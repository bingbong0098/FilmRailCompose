package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FavouriteApi {

    @GET("favorite")
    suspend fun getFavoriteList(
        @Query("user_id") userId: String?,
        @Query("page") page: Int
    ): Response<List<Movie>?>


}
