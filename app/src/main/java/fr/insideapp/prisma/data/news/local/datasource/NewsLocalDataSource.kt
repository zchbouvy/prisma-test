package fr.insideapp.prisma.data.news.local.datasource

import fr.insideapp.prisma.data.news.local.model.NewsDb
import fr.insideapp.prisma.domain.news.model.News
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun insertAll(news: List<News>)
    fun getAll(): Flow<List<NewsDb>>
    fun getFavorites(): Flow<List<NewsDb>>
    suspend fun update(news: News)
    suspend fun delete(news: News)
}