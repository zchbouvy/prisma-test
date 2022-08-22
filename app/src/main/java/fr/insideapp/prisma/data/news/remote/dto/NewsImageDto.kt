package fr.insideapp.prisma.data.news.remote.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsImageDto(
    val original: NewsImageUrlDto?
)