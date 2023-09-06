package com.example.ninenews.network

import com.example.ninenews.model.NewsAFR
import retrofit2.http.GET

/**
 * Retrofit service object for creating api calls
 */
interface NewsApiService {
    @GET("afr")
    suspend fun getNews(): NewsAFR
}
