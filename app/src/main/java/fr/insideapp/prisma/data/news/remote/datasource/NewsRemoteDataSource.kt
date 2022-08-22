package fr.insideapp.prisma.data.news.remote.datasource

import fr.insideapp.prisma.data.news.remote.dto.NewsDto

interface NewsRemoteDataSource {
    suspend fun fetchAll(): NewsDto
}