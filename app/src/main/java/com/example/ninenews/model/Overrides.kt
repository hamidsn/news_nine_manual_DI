package com.example.ninenews.model

import com.google.gson.annotations.SerializedName


data class Overrides(

    @SerializedName("overrideAbstract") var overrideAbstract: String? = null,
    @SerializedName("overrideHeadline") var overrideHeadline: String? = null

)