package fr.insideapp.prisma.domain.news.usecase

import fr.insideapp.prisma.domain.news.NewsRepository
import fr.insideapp.prisma.domain.news.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(): Flow<List<News>> {
        return newsRepository.getFavoriteNews()
    }
}