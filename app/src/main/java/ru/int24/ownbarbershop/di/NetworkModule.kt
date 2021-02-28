package ru.int24.ownbarbershop.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.int24.ownbarbershop.config.DefConfig
import ru.int24.ownbarbershop.network.ApiYclients
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient():OkHttpClient{
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DefConfig.url)
                .build()
    }

    @Provides
    @Singleton
    fun provideRemoteApiYclients(retrofit: Retrofit): ApiYclients = retrofit.create(ApiYclients::class.java)

}