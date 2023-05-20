package com.blackstoneeit.ncgrtask.data.model.response

import com.google.gson.annotations.SerializedName

data class MostViewedResponse(
    @SerializedName("results")
    val results: ArrayList<Results> = arrayListOf()
)
