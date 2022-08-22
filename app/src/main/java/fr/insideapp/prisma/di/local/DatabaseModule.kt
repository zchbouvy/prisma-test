package fr.insideapp.prisma.di.local

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.insideapp.prisma.data.common.PrismaRoomDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val PRISMA_DB_NAME = "PrismaDataBase"

    @Singleton
    @Provides
    fun provideAtfRoomDatabase(application: Application): PrismaRoomDatabase {
        return Room.databaseBuilder(
            application,
            PrismaRoomDatabase::class.java,
            PRISMA_DB_NAME
        ).build()
    }
}
