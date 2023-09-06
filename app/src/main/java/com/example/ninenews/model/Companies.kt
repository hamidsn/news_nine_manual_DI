package com.example.ninenews.model

import com.google.gson.annotations.SerializedName


data class Companies(

    @SerializedName("abbreviatedName") var abbreviatedName: String? = null,
    @SerializedName("companyCode") var companyCode: String? = null,
    @SerializedName("companyName") var companyName: String? = null,
    @SerializedName("companyNumber") var companyNumber: String? = null,
    @SerializedName("exchange") var exchange: String? = null,
    @SerializedName("id") var id: Int? = null

)