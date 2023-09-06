package com.example.ninenews.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ninenews.NineNewsApplication
import com.example.ninenews.data.NewsRepository
import com.example.ninenews.model.Assets
import com.example.ninenews.util.validateAndSort
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * Manual DI: The value for the constructor parameter comes from the application container
 * Because the Android framework does not allow
 * a ViewModel to be passed values in the constructor when created,
 * we implement a ViewModelProvider.
 * Factory object, which lets us get around this limitation.
 */
class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    // We only need to update mutable State of NewsUiState Composables are listening to it
    var newsUiState: NewsUiState by mutableStateOf(NewsUiState.Loading)
        private set

    /**
     * Call getNewsList() on init so we have some daya to show.
     */
    init {
        getNewsList()
    }

    /**
     * Gets news from the API Retrofit service
     * Status is Loading by default, then
     * set the result as Error, Empty or Success
     */
    fun getNewsList() {
        viewModelScope.launch {
            newsUiState = NewsUiState.Loading
            newsUiState = try {
                if (newsRepository.getNews().assets.size == 0) {
                    NewsUiState.Empty
                } else {
                    newsRepository.getNews().assets.validateAndSort().let {
                        if (it.size == 0) {
                            NewsUiState.Error("malformed response")
                        } else {
                            NewsUiState.Success(it)
                        }
                    }
                }
            } catch (e: IOException) {
                NewsUiState.Error(e.message ?: "")
            } catch (e: HttpException) {
                NewsUiState.Error(e.message ?: "")
            }
        }
    }

    /**
     * The APPLICATION_KEY is part of the ViewModelProvider.AndroidViewModelFactory.Companion object
     * It is used to find the NineNewsApplication object, in which we hold the container
     * used to retrieve the repository for dependency injection.
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as NineNewsApplication)
                val newsRepository = application.container.newsRepository
                NewsViewModel(newsRepository = newsRepository)
            }
        }
    }

}

/**
 * UI state for the Home screen
 */
sealed interface NewsUiState {
    data class Success(val assets: ArrayList<Assets>) : NewsUiState
    data class Error(val error: String) : NewsUiState
    object Loading : NewsUiState
    object Empty : NewsUiState
}


