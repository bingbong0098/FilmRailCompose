package com.platform.mediacenter.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.platform.mediacenter.utils.Constants.ACCESS_TOKEN
import com.platform.mediacenter.utils.Constants.API_KEY
import com.platform.mediacenter.utils.Constants.BASE_URL
import com.platform.mediacenter.utils.Constants.TIME_OUT_IN_SECONDS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("api-key", API_KEY)
                .addHeader("authorization", "Bearer $ACCESS_TOKEN")
                .build()
            chain.proceed(request)

        }
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()

}