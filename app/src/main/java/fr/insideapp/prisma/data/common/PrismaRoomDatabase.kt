package fr.insideapp.prisma.data.common

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.insideapp.prisma.data.news.local.dao.NewsDao
import fr.insideapp.prisma.data.news.local.model.NewsDb

@Database(
    entities = [NewsDb::class],
    version = 1
)
abstract class PrismaRoomDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}