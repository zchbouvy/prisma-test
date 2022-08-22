package fr.insideapp.prisma.di.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.insideapp.prisma.data.news.remote.datasource.NewsRemoteDataSource
import fr.insideapp.prisma.data.news.remote.datasource.NewsRemoteDataSourceImpl
import fr.insideapp.prisma.data.news.remote.api.PrismaApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(prismaApi: PrismaApi): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(prismaApi)
    }
}
