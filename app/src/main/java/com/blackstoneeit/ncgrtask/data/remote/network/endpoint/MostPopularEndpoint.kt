package com.blackstoneeit.ncgrtask.data.remote.network.endpoint

import com.blackstoneeit.ncgrtask.data.model.response.MostViewedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MostPopularEndpoint {

    @GET("viewed/{period}.json")
    suspend fun getMostViewed(
        @Path("period") period: String,
        @Query("api-key") apiKey: String?
    ): Response<MostViewedResponse>

}