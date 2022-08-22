package fr.insideapp.prisma.data.news.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsDb(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "published")
    val published: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?,
    @ColumnInfo(name = "is_favorite")
    val isFavorite: Boolean
)

data class NewsDbUpdate(
    val id: String,
    val title: String?,
    val published: String?,
    @ColumnInfo(name = "image_url")
    val imageUrl: String?
)