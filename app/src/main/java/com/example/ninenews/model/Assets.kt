package com.example.ninenews.model

import com.google.gson.annotations.SerializedName

data class Assets(

    @SerializedName("acceptComments") var acceptComments: Boolean? = null,
    @SerializedName("assetType") var assetType: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("authors") var authors: ArrayList<Authors> = arrayListOf(),
    @SerializedName("body") var body: String? = null,
    @SerializedName("byLine") var byLine: String? = null,
    @SerializedName("categories") var categories: ArrayList<Categories> = arrayListOf(),
    @SerializedName("companies") var companies: ArrayList<Companies> = arrayListOf(),
    @SerializedName("headline") var headline: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("indexHeadline") var indexHeadline: String? = null,
    @SerializedName("lastModified") var lastModified: Long? = null,
    @SerializedName("legalStatus") var legalStatus: String? = null,
    @SerializedName("live") var live: Boolean? = null,
    @SerializedName("liveArticleSummary") var liveArticleSummary: String? = null,
    @SerializedName("numberOfComments") var numberOfComments: Int? = null,
    @SerializedName("relatedAssets") var relatedAssets: ArrayList<String> = arrayListOf(),
    @SerializedName("relatedImages") var relatedImages: ArrayList<RelatedImages> = arrayListOf(),
    @SerializedName("signPost") var signPost: String? = null,
    @SerializedName("sources") var sources: ArrayList<Sources> = arrayListOf(),
    @SerializedName("sponsored") var sponsored: Boolean? = null,
    @SerializedName("theAbstract") var theAbstract: String? = null,
    @SerializedName("tabletHeadline") var tabletHeadline: String? = null,
    @SerializedName("overrides") var overrides: Overrides? = Overrides(),
    @SerializedName("extendedAbstract") var extendedAbstract: String? = null,
    @SerializedName("timeStamp") var timeStamp: Long? = null

)

fun Assets.sortByImageWidth(): Assets {
    return this.copy(relatedImages = ArrayList(relatedImages.sortedByDescending { it.width }
        .filter { it.width != 0 }))
}