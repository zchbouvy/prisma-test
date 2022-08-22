package fr.insideapp.prisma.domain.news.usecase

import fr.insideapp.prisma.domain.news.NewsRepository
import fr.insideapp.prisma.domain.news.model.News
import javax.inject.Inject

class DeleteNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke(news: News) {
        newsRepository.deleteNews(news)
    }
}