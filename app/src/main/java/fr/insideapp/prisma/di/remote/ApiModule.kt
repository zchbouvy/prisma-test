package fr.insideapp.prisma.di.remote

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.insideapp.prisma.data.news.remote.api.PrismaApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    private const val BASE_URL = "https://gist.githubusercontent.com/julienbanse/34cdfbd1c094b2dddffce2b5d5533d6b/raw/15b5f322838e08bf8a38985b7aa94f6c758d6741/"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    @Singleton
    @Provides
    fun providePrismaRetrofit(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        moshiConverterFactory: MoshiConverterFactory,
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(moshiConverterFactory)
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build()
        )
        .build()

    @Singleton
    @Provides
    fun providePrismaApi(retrofit: Retrofit): PrismaApi = retrofit.create(PrismaApi::class.java)
}
