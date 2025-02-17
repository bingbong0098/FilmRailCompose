package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.MovieApi
import com.platform.mediacenter.data.model.movie.MovieResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class MovieRepository @Inject constructor(private val api: MovieApi) : BaseApiResponse() {
    suspend fun getMovies(page: Int): NetworkResult<MovieResponse> =
        safeApiCall { api.getMovies(page) }

    suspend fun getMovieByGenreId(videoId :String? ,page: Int): NetworkResult<MovieResponse> =
        safeApiCall { api.getMovieByGenreId(videoId ,page) }

    suspend fun getMovieByCountryId(videoId :String? ,page: Int): NetworkResult<MovieResponse> =
        safeApiCall { api.getMovieByCountryId(videoId ,page) }

    suspend fun getMovieByStarId(videoId :String? ,page: Int): NetworkResult<MovieResponse> =
        safeApiCall { api.getMovieByStarId(videoId ,page) }

}