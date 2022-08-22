package fr.insideapp.prisma.domain.news

import fr.insideapp.prisma.domain.news.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun fetchAllNews()
    suspend fun getAllNews(): Flow<List<News>>
    suspend fun getFavoriteNews(): Flow<List<News>>
    suspend fun updateNews(news: News)
    suspend fun deleteNews(news: News)
}