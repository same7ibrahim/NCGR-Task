package com.blackstoneeit.ncgrtask.data.remote.network

import okhttp3.Authenticator
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession


class RetrofitClient(builder: Builder) {

    private val retrofit: Retrofit

    init {
        val baseUrl = builder.baseUrl
        val readTimeOut = builder.readTimeOutSeconds ?: DEFAULT_TIME_OUT_SECONDS
        val writeTimeout = builder.writeTimeoutSeconds ?: DEFAULT_TIME_OUT_SECONDS
        val connectTimeout = builder.connectTimeoutSeconds ?: DEFAULT_TIME_OUT_SECONDS
        val useLoggerInterceptor = builder.useLoggerInterceptor
        val interceptors = builder.interceptors
        val authenticator = builder.authenticator
        val cache = builder.cache

        try {

            val okHttpClient = OkHttpClient().newBuilder()
                .useDefaultLoggerInterceptor(useLoggerInterceptor)
                .addInterceptors(interceptors)
                .readTimeout(readTimeOut, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .cache(cache)
                .authenticator(authenticator)
                .hostnameVerifier { _: String?, _: SSLSession? -> true }
                .build()

            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


    fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }

    class Builder(internal val baseUrl: String) {

        internal var connectTimeoutSeconds: Long? = null
        internal var readTimeOutSeconds: Long? = null
        internal var writeTimeoutSeconds: Long? = null
        internal var useLoggerInterceptor: Boolean = false
        private var callAdapters: MutableList<CallAdapter.Factory> = mutableListOf()
        internal var interceptors: MutableList<Interceptor> = mutableListOf()
        internal var authenticator: Authenticator = Authenticator.NONE
        internal var cache: Cache? = null


        fun build(buildBlock: Builder.() -> Unit = {}): RetrofitClient {
            buildBlock()
            return RetrofitClient(this)
        }

        fun useDefaultLoggerInterceptor() = apply {
            this.useLoggerInterceptor = true
        }

        fun addCallAdapter(callAdapterFactory: CallAdapter.Factory) = apply {
            this.callAdapters.add(callAdapterFactory)
        }

        fun addInterceptor(interceptor: Interceptor) = apply {
            this.interceptors.add(interceptor)
        }

    }


    companion object {
        private const val DEFAULT_TIME_OUT_SECONDS = 60L
    }
}

