package fr.insideapp.prisma.data.news.repository

import fr.insideapp.prisma.data.news.local.datasource.NewsLocalDataSource
import fr.insideapp.prisma.data.news.mapper.NewsLocalMapper
import fr.insideapp.prisma.data.news.mapper.NewsRemoteMapper
import fr.insideapp.prisma.data.news.remote.datasource.NewsRemoteDataSource
import fr.insideapp.prisma.domain.news.NewsRepository
import fr.insideapp.prisma.domain.news.model.News
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource
) : NewsRepository {

    override suspend fun fetchAllNews() {
        newsRemoteDataSource.fetchAll().also { response ->
            val news = NewsRemoteMapper.mapFromDto(response)
            newsLocalDataSource.insertAll(news)
        }
    }

    override suspend fun getAllNews(): Flow<List<News>> {
        return newsLocalDataSource.getAll().map {
            NewsLocalMapper.mapFromDb(it)
        }
    }

    override suspend fun getFavoriteNews(): Flow<List<News>> {
        return newsLocalDataSource.getFavorites().map {
            NewsLocalMapper.mapFromDb(it)
        }
    }

    override suspend fun updateNews(news: News) {
        newsLocalDataSource.update(news)
    }

    override suspend fun deleteNews(news: News) {
        newsLocalDataSource.delete(news)
    }
}