package com.example.ninenews.fake

import com.example.ninenews.data.NewsRepository
import com.example.ninenews.model.NewsAFR

//leverage the fake API service
class FakeSuccessNetworkNewsRepository : NewsRepository {
    override suspend fun getNews(): NewsAFR {
        return FakeDataSource.successNews
    }


}