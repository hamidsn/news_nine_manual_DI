package com.example.ninenews.fake

import com.example.ninenews.model.NewsAFR
import com.example.ninenews.network.NewsApiService

//By passing the fake API service,
// any call to the NewsApiService from repository runs a call to the FakeNewsApiService
class FakeNewsApiService : NewsApiService {
    override suspend fun getNews(): NewsAFR {
        return FakeDataSource.successNews
    }
}