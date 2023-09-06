package com.example.ninenews.fake

import com.example.ninenews.data.NetworkNewsRepository
import junit.framework.TestCase.assertEquals

import kotlinx.coroutines.test.runTest
import org.junit.Test

class NetworkNewsRepositoryTest {

    @Test
    fun networkNewsRepository_getNews_verifyNewsList() = runTest {

        val repository = NetworkNewsRepository(
            newsApiService = FakeNewsApiService()
        )
        assertEquals(FakeDataSource.successNews, repository.getNews())
    }
}