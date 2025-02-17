package com.platform.mediacenter.di

import com.platform.mediacenter.data.api.ContinueWatchingApi
import com.platform.mediacenter.data.api.CountryApi
import com.platform.mediacenter.data.api.DetailApi
import com.platform.mediacenter.data.api.DeviceManagerApi
import com.platform.mediacenter.data.api.FavouriteApi
import com.platform.mediacenter.data.api.GenreApi
import com.platform.mediacenter.data.api.HomeApi
import com.platform.mediacenter.data.api.LiveTvApi
import com.platform.mediacenter.data.api.ProfileApi
import com.platform.mediacenter.data.api.MovieApi
import com.platform.mediacenter.data.api.SearchApi
import com.platform.mediacenter.data.api.TvSerisApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi =
        retrofit.create(HomeApi::class.java)

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideTvSeriesApi(retrofit: Retrofit): TvSerisApi =
        retrofit.create(TvSerisApi::class.java)

    @Provides
    @Singleton
    fun provideLiveTvApi(retrofit: Retrofit): LiveTvApi =
        retrofit.create(LiveTvApi::class.java)

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): ProfileApi =
        retrofit.create(ProfileApi::class.java)

    @Provides
    @Singleton
    fun provideGenreApi(retrofit: Retrofit): GenreApi =
        retrofit.create(GenreApi::class.java)

    @Provides
    @Singleton
    fun provideCountryApi(retrofit: Retrofit): CountryApi =
        retrofit.create(CountryApi::class.java)

    @Provides
    @Singleton
    fun provideFavouriteApi(retrofit: Retrofit): FavouriteApi =
        retrofit.create(FavouriteApi::class.java)

    @Provides
    @Singleton
    fun provideDetailApi(retrofit: Retrofit): DetailApi =
        retrofit.create(DetailApi::class.java)

    @Provides
    @Singleton
    fun provideContinueWatchingApi(retrofit: Retrofit): ContinueWatchingApi =
        retrofit.create(ContinueWatchingApi::class.java)

    @Provides
    @Singleton
    fun provideSearchApi(retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)

    @Provides
    @Singleton
    fun provideDeviceManagerApi(retrofit: Retrofit): DeviceManagerApi =
        retrofit.create(DeviceManagerApi::class.java)

}