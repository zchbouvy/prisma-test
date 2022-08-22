package fr.insideapp.prisma.domain.news.model

data class News(
    val id: String,
    val title: String?,
    val imageUrl: String?,
    val published: String?,
    var isFavorite: Boolean,
)