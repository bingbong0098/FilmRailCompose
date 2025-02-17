package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.movie.MovieResponse
import com.platform.mediacenter.data.model.tvseries.TvSerisResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvSerisApi {
    @GET("tvseries")
    suspend fun getSeries(@Query("page") page: Int): Response<TvSerisResponse>

}