package com.blackstoneeit.ncgrtask.data.repository

import com.blackstoneeit.ncgrtask.data.model.response.MostViewedResponse
import com.blackstoneeit.ncgrtask.data.remote.network.endpoint.MostPopularEndpoint
import com.blankj.utilcode.util.LogUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MostPopularRepository @Inject constructor(
    private val mostPopularEndpoint: MostPopularEndpoint,
) {
    fun getMostViewed(period: String, apiKey: String): Flow<Response<MostViewedResponse>> =
        flow {
           val result = mostPopularEndpoint.getMostViewed(period,apiKey)
            emit(result)
        }.catch {
            LogUtils.e(it)
        }

}