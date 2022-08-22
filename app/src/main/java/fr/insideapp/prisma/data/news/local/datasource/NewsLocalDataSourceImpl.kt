package fr.insideapp.prisma.data.news.local.datasource

import fr.insideapp.prisma.data.common.PrismaRoomDatabase
import fr.insideapp.prisma.data.news.local.model.NewsDb
import fr.insideapp.prisma.data.news.mapper.NewsLocalMapper
import fr.insideapp.prisma.domain.news.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val database: PrismaRoomDatabase
) : NewsLocalDataSource {

    private val newsDao by lazy { database.newsDao() }

    override suspend fun insertAll(news: List<News>) {
        val newsDb = NewsLocalMapper.mapToDb(news)
        newsDb.forEach { newsDao.insertOrUpdate(it) }
    }

    override fun getAll(): Flow<List<NewsDb>> = newsDao.getAll()

    override fun getFavorites(): Flow<List<NewsDb>> = newsDao.getFavorites()

    override suspend fun update(news: News) {
        val newsDb = NewsLocalMapper.mapToNewsDb(news)
        newsDao.update(newsDb)
    }

    override suspend fun delete(news: News) {
        val newsDb = NewsLocalMapper.mapToNewsDb(news)
        newsDao.delete(newsDb)
    }
}