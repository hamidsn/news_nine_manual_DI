package com.example.ninenews.fake

import com.example.ninenews.rule.TestDispatcherRule
import com.example.ninenews.ui.screens.NewsUiState
import com.example.ninenews.ui.screens.NewsViewModel
import junit.framework.TestCase
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class NewsViewModelTest {
    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun newsViewModel_getNews_verifyNewsUiStateSuccess() =
        //coroutine test library provides the runTest()
        runTest {
            val newsViewModel = NewsViewModel(
                newsRepository = FakeSuccessNetworkNewsRepository()
            )
            TestCase.assertEquals(
                NewsUiState.Success(FakeDataSource.successNews.assets),
                newsViewModel.newsUiState
            )
        }

    @Test
    fun newsViewModel_getNews_verifyNewsUiStateEmpty() =
        runTest {
            val newsViewModel = NewsViewModel(
                newsRepository = FakEmptyNetworkNewsRepository()
            )
            TestCase.assertEquals(
                NewsUiState.Empty,
                newsViewModel.newsUiState
            )
        }
}