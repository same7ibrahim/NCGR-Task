package com.blackstoneeit.ncgrtask.data.model.response

import com.google.gson.annotations.SerializedName

data class Results(

    @SerializedName("uri")
    val uri: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("asset_id")
    val assetId: String?,
    @SerializedName("source")
    val source: String?,
    @SerializedName("published_date")
    val publishedDate: String?,
    @SerializedName("updated")
    val updated: String?,
    @SerializedName("section")
    val section: String?,
    @SerializedName("subsection")
    val subsection: String?,
    @SerializedName("nytdsection")
    val nytdsection: String?,
    @SerializedName("adx_keywords")
    val adxKeywords: String?,
    @SerializedName("column")
    val column: String?,
    @SerializedName("byline")
    val byline: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("abstract")
    val abstract: String?,
    @SerializedName("des_facet")
    val desFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("org_facet")
    val orgFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("per_facet")
    val perFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("geo_facet")
    val geoFacet: ArrayList<String> = arrayListOf(),
    @SerializedName("media")
    val media: ArrayList<Media> = arrayListOf(),
    @SerializedName("eta_id")
    val etaId: Int?
)
