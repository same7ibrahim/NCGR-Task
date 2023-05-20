package com.blackstoneeit.ncgrtask.data.model.response

import com.google.gson.annotations.SerializedName

data class MediaMetaData(

    @SerializedName("url")
    val url: String?,
    @SerializedName("format")
    val format: String?,
    @SerializedName("height")
    val height: Int?,
    @SerializedName("width")
    val width: Int?

)