package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.FavouriteApi
import com.platform.mediacenter.data.model.Movie
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class FavouriteRepository @Inject constructor(private val api: FavouriteApi) : BaseApiResponse() {
    suspend fun getFavoriteList(userId: String, page: Int): NetworkResult<List<Movie>?> =
        safeApiCall { api.getFavoriteList(userId,page) }
}