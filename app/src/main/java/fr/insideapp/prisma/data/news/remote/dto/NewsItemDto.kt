package fr.insideapp.prisma.data.news.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsItemDto(
    @Json(name = "universalUniqueIdentifier")
    val id: String,
    val title: String?,
    val medias: NewsMediasDto?,
    val published: String?,
)