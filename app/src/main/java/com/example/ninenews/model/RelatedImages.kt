package com.example.ninenews.model

import com.google.gson.annotations.SerializedName


data class RelatedImages(

    @SerializedName("assetType") var assetType: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("authors") var authors: ArrayList<String> = arrayListOf(),
    @SerializedName("brands") var brands: ArrayList<String> = arrayListOf(),
    @SerializedName("categories") var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("description") var description: String? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("lastModified") var lastModified: Long? = null,
    @SerializedName("photographer") var photographer: String? = null,
    @SerializedName("sponsored") var sponsored: Boolean? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("width") var width: Int? = null,
    @SerializedName("xLarge@2x") var xLarge2x: String? = null,
    @SerializedName("large@2x")
    var large2x: String? = null,
    @SerializedName("timeStamp")
    var timeStamp: Long? = null

)