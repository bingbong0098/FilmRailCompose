package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.genre.GenreResponse
import com.platform.mediacenter.data.model.home.HomeContentResponse
import retrofit2.Response
import retrofit2.http.GET

interface GenreApi {

    @GET("all_genre")
    suspend fun getGenre(): Response<GenreResponse>
}