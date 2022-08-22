package fr.insideapp.prisma.domain.news.usecase

import fr.insideapp.prisma.domain.news.NewsRepository
import javax.inject.Inject

class FetchAllNewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {

    suspend operator fun invoke() {
        return newsRepository.fetchAllNews()
    }
}