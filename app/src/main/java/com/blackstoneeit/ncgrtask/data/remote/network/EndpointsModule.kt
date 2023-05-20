package com.blackstoneeit.ncgrtask.data.remote.network

import com.blackstoneeit.ncgrtask.data.remote.network.endpoint.MostPopularEndpoint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class EndpointsModule {

    @Singleton
    @Provides
    fun mostPopularEndpoint(restClient: RetrofitClient): MostPopularEndpoint =
        restClient.create(MostPopularEndpoint::class.java)

}
