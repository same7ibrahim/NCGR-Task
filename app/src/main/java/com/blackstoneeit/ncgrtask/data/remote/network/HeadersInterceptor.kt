package com.blackstoneeit.ncgrtask.data.remote.network

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeadersInterceptor @Inject constructor() :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val requestBuilder = original.newBuilder()

        requestBuilder.header("accept", "application/json")
        requestBuilder.header("Content-Type", "application/json")

        requestBuilder.method(original.method, original.body)
        val request = requestBuilder.build()

        val response = chain.proceed(request)
        val responseBody = response.peekBody(2048).string()
        val responseCode = response.code
        val mediaType = response.body!!.contentType()
        return try {
            val json = JSONObject(responseBody)
            if (json.has(REQUEST_STATUS) && !json.getBoolean(REQUEST_STATUS)) {
                response.getResponse(responseBody, mediaType, 400)
            } else if (json.has(REQUEST_STATUS)) {
                response.getResponse(responseBody, mediaType, 200)
            } else if (json.has(RESULT)) {
                if (json.getJSONObject(RESULT).has(CODE) && json.getJSONObject(RESULT)
                        .getInt(CODE) == 200
                ) {
                    response.getResponse(responseBody, mediaType, 200)
                } else {
                    response.getResponse(responseBody, mediaType, 400)
                }
            } else if (json.has(CODE_SMALL)) {
                if (json.getInt(CODE_SMALL) == 200
                ) {
                    response.getResponse(responseBody, mediaType, 200)
                } else {
                    response.getResponse(responseBody, mediaType, 400)
                }
            } else {
                response.getResponse(responseBody, mediaType, responseCode)
            }
        } catch (ignored: Exception) {
            response
        }
    }
}

fun Response.getResponse(responseBody: String, mediaType: MediaType?, code: Int): Response {
    return this.newBuilder().body(responseBody.toResponseBody(mediaType)).code(code).build()
}

private const val REQUEST_STATUS = "IsSuccessful"
private const val RESULT = "result"
private const val CODE = "Code"
private const val CODE_SMALL = "code"
