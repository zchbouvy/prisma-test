package fr.insideapp.prisma.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.insideapp.prisma.domain.news.model.News
import fr.insideapp.prisma.domain.news.usecase.GetFavoriteNewsUseCase
import fr.insideapp.prisma.domain.news.usecase.UpdateNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteNewsUseCase: GetFavoriteNewsUseCase,
    private val updateNewsUseCase: UpdateNewsUseCase
) : ViewModel() {

    fun getFavoriteNews() = liveData(Dispatchers.IO) {
        getFavoriteNewsUseCase()
            .onEach { emit(it) }
            .collect()
    }

    fun setFavoriteNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            updateNewsUseCase(news)
        }
    }
}