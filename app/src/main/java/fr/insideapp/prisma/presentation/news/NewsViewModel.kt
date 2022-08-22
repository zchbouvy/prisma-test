package fr.insideapp.prisma.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.insideapp.prisma.domain.news.model.News
import fr.insideapp.prisma.domain.news.usecase.DeleteNewsUseCase
import fr.insideapp.prisma.domain.news.usecase.FetchAllNewsUseCase
import fr.insideapp.prisma.domain.news.usecase.GetAllNewsUseCase
import fr.insideapp.prisma.domain.news.usecase.UpdateNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val fetchAllNewsUseCase: FetchAllNewsUseCase,
    private val getAllNewsUseCase: GetAllNewsUseCase,
    private val updateNewsUseCase: UpdateNewsUseCase,
    private val deleteNewsUseCase: DeleteNewsUseCase
) : ViewModel() {

    init {
        fetchAllNews()
    }

    private fun fetchAllNews() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAllNewsUseCase()
        }
    }

    fun getAllNews() = liveData(Dispatchers.IO) {
        getAllNewsUseCase()
            .onEach { emit(it) }
            .collect()
    }

    fun setFavoriteNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNewsUseCase(news)
        }
    }

    fun deleteNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteNewsUseCase(news)
        }
    }
}