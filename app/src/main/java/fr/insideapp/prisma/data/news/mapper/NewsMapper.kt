package fr.insideapp.prisma.data.news.mapper

import fr.insideapp.prisma.data.news.local.model.NewsDb
import fr.insideapp.prisma.data.news.remote.dto.NewsDto
import fr.insideapp.prisma.data.news.remote.dto.NewsItemDto
import fr.insideapp.prisma.domain.news.model.News

object NewsRemoteMapper {

    fun mapFromDto(newsDto: NewsDto): List<News> {
        return newsDto.data?.items?.map { mapFromNewsDto(it) } ?: emptyList()
    }

    private fun mapFromNewsDto(newsItemDto: NewsItemDto): News {
        return News(
            id = newsItemDto.id,
            title = newsItemDto.title,
            imageUrl = newsItemDto.medias?.images?.firstOrNull()?.original?.url,
            published = newsItemDto.published,
            isFavorite = false
        )
    }
}

object NewsLocalMapper {

    fun mapToDb(news: List<News>): List<NewsDb> {
        return news.map { mapToNewsDb(it) }
    }

    fun mapToNewsDb(news: News): NewsDb {
        return NewsDb(
            id = news.id,
            title = news.title,
            imageUrl = news.imageUrl,
            published = news.published,
            isFavorite = news.isFavorite
        )
    }

    fun mapFromDb(newsDb: List<NewsDb>): List<News> {
        return newsDb.map { mapFromNewsDb(it) }
    }

    private fun mapFromNewsDb(newsDb: NewsDb): News {
        return News(
            id = newsDb.id,
            title = newsDb.title,
            imageUrl = newsDb.imageUrl,
            published = newsDb.published,
            isFavorite = newsDb.isFavorite
        )
    }
}