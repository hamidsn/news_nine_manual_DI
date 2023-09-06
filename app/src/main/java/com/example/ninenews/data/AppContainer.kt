package com.example.ninenews.data

import com.example.ninenews.BuildConfig
import com.example.ninenews.network.NewsApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val newsRepository: NewsRepository
}

/**
 * A container is an object that contains the dependencies that the app requires.
 */
class DefaultAppContainer : AppContainer {

    //getting base url from gradle is safer than strings
    private val baseUrl = BuildConfig.BASE_URL

    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
    override val newsRepository: NewsRepository by lazy {
        NetworkNewsRepository(retrofitService)
    }

}