package fr.insideapp.prisma.data.news.remote.api

import fr.insideapp.prisma.data.news.remote.dto.NewsDto
import retrofit2.http.GET

interface PrismaApi {

    @GET(NEW_PATH)
    suspend fun getAllNews(): NewsDto

    companion object {
        const val NEW_PATH = "news.json"
    }
}