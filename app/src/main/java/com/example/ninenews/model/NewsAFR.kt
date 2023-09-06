package com.example.ninenews.model

import com.google.gson.annotations.SerializedName


/**
 * All data classes are created by https://json2kt.com/download.php
 */
data class NewsAFR(

    @SerializedName("assetType") var assetType: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("authors") var authors: ArrayList<String> = arrayListOf(),
    @SerializedName("categories") var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("displayName") var displayName: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("lastModified") var lastModified: Long? = null,
    @SerializedName("onTime") var onTime: Long? = null,
    @SerializedName("relatedAssets") var relatedAssets: ArrayList<String> = arrayListOf(),
    @SerializedName("relatedImages") var relatedImages: ArrayList<String> = arrayListOf(),
    @SerializedName("sponsored") var sponsored: Boolean? = null,
    @SerializedName("assets") var assets: ArrayList<Assets> = arrayListOf(),
    @SerializedName("timeStamp") var timeStamp: Long? = null

)