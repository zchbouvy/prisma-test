package fr.insideapp.prisma.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.insideapp.prisma.data.news.local.datasource.NewsLocalDataSource
import fr.insideapp.prisma.data.news.remote.datasource.NewsRemoteDataSource
import fr.insideapp.prisma.data.news.repository.NewsRepositoryImpl
import fr.insideapp.prisma.domain.news.NewsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}
