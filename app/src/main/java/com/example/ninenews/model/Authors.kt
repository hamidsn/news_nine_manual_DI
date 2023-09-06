package com.example.ninenews.model

import com.google.gson.annotations.SerializedName


data class Authors(

    @SerializedName("email") var email: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("relatedAssets") var relatedAssets: ArrayList<String> = arrayListOf(),
    @SerializedName("relatedImages") var relatedImages: ArrayList<RelatedImages> = arrayListOf(),
    @SerializedName("title") var title: String? = null

)