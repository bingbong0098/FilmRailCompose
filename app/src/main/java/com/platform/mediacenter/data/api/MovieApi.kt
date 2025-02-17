package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.movie.MovieResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movies")
    suspend fun getMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("content_by_genre_id")
    suspend fun getMovieByGenreId(
        @Query("id") id: String?,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("content_by_country_id")
    suspend fun getMovieByCountryId(
        @Query("id") id: String?,
        @Query("page") page: Int
    ): Response<MovieResponse>

    @GET("content_by_star_id")
    suspend fun getMovieByStarId(
        @Query("id") id: String?,
        @Query("page") page: Int
    ): Response<MovieResponse>



}