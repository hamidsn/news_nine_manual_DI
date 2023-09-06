package com.example.ninenews.data

import com.example.ninenews.model.NewsAFR
import com.example.ninenews.network.NewsApiService

interface NewsRepository {
    suspend fun getNews(): NewsAFR
}

class NetworkNewsRepository(private val newsApiService: NewsApiService) :
    NewsRepository {
    override suspend fun getNews(): NewsAFR = newsApiService.getNews()

}