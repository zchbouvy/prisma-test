package fr.insideapp.prisma.di.local

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.insideapp.prisma.data.common.PrismaRoomDatabase
import fr.insideapp.prisma.data.news.local.datasource.NewsLocalDataSource
import fr.insideapp.prisma.data.news.local.datasource.NewsLocalDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideNewsLocaleDataSource(prismaRoomDatabase: PrismaRoomDatabase): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(prismaRoomDatabase)
    }
}
