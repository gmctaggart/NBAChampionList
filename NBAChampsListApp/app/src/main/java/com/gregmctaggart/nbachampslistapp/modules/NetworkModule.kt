package com.gregmctaggart.nbachampslistapp.modules

import com.google.gson.GsonBuilder
import com.gregmctaggart.nbachampslistapp.ListAPI
import com.gregmctaggart.nbachampslistapp.NBAChampionListDeserializer
import com.gregmctaggart.nbachampslistapp.NBAChampionListResponse
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    companion object {
        const val BASE_URL = "http://www.mocky.io/"
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLogger)

        val gsonBuilder = GsonBuilder()
        val gson = GsonBuilder().create()
        val deserializer = NBAChampionListDeserializer(gson)
        gsonBuilder.registerTypeAdapter(NBAChampionListResponse::class.java, deserializer)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideListApi(retrofit: Retrofit): ListAPI {
        return retrofit.create(ListAPI::class.java)
    }
}