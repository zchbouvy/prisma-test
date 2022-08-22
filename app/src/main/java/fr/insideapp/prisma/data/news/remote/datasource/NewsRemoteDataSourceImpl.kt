package fr.insideapp.prisma.data.news.remote.datasource

import fr.insideapp.prisma.data.news.remote.api.PrismaApi
import fr.insideapp.prisma.data.news.remote.dto.NewsDto
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val prismaApi: PrismaApi
) : NewsRemoteDataSource {

    override suspend fun fetchAll(): NewsDto {
        return prismaApi.getAllNews()
    }
}