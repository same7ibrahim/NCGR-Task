package com.blackstoneeit.ncgrtask.data.remote.network

import com.blackstoneeit.ncgrtask.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun crmRestClient(
        headersInterceptor: HeadersInterceptor,
    ) =
        RetrofitClient.Builder(BuildConfig.BASE_URL)
            .build {
                useDefaultLoggerInterceptor()
                addInterceptor(headersInterceptor)
            }
}
