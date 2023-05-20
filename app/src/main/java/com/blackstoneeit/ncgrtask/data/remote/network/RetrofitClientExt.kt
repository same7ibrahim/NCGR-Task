package com.blackstoneeit.ncgrtask.data.remote.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


fun OkHttpClient.Builder.addInterceptors(interceptors: List<Interceptor>) = apply {
    interceptors.forEach { interceptor ->
        addInterceptor(interceptor)
    }
}

fun OkHttpClient.Builder.useDefaultLoggerInterceptor(useLoggerInterceptor: Boolean) = apply {
    if (useLoggerInterceptor) {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        addInterceptor(logger)
    }
}

