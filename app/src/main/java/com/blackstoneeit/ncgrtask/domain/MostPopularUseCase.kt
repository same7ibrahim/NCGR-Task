package com.blackstoneeit.ncgrtask.domain

import com.blackstoneeit.ncgrtask.data.repository.MostPopularRepository
import javax.inject.Inject

class MostPopularUseCase @Inject constructor(
    private val mostPopularRepository: MostPopularRepository) {

      fun getMostPopular(period: String, apiKey: String) =
        mostPopularRepository.getMostViewed(period, apiKey)
}