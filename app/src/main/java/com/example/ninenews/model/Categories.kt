package com.example.ninenews.model

import com.google.gson.annotations.SerializedName


data class Categories(

    @SerializedName("name") var name: String? = null,
    @SerializedName("orderNum") var orderNum: Int? = null,
    @SerializedName("sectionPath") var sectionPath: String? = null

)