package fr.insideapp.prisma.data.news.local.dao

import androidx.room.Delete
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import fr.insideapp.prisma.data.news.local.model.NewsDb
import fr.insideapp.prisma.data.news.local.model.NewsDbUpdate
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM news")
    fun getAll(): Flow<List<NewsDb>>

    @Query("SELECT * FROM news WHERE is_favorite = 1")
    fun getFavorites(): Flow<List<NewsDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsDb>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(news: NewsDb): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(news: NewsDb)

    @Update(onConflict = OnConflictStrategy.REPLACE, entity = NewsDb::class)
    suspend fun update(news: NewsDbUpdate)

    @Delete
    suspend fun delete(news: NewsDb)

    suspend fun insertOrUpdate(news: NewsDb) {
        val id = insert(news)
        if (id == -1L) {
            val newsDbUpdate = NewsDbUpdate(
                id = news.id,
                title = news.title,
                published = news.published,
                imageUrl = news.imageUrl
            )
            update(newsDbUpdate)
        }
    }
}