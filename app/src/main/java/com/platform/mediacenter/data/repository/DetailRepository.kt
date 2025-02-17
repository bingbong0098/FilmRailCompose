package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.DetailApi
import com.platform.mediacenter.data.remote.BaseApiResponse
import javax.inject.Inject

class DetailRepository @Inject constructor(private val api: DetailApi) : BaseApiResponse() {

    suspend fun getSingleDetails(type: String?, id: String?) =
        safeApiCall { api.getSingleDetails(type, id) }

    suspend fun getAllComments(videoId: String?) =
        safeApiCall { api.getAllComments(videoId) }

    suspend fun getFavoriteStatus(userId: String?, videoId: String?) =
        safeApiCall { api.verifyFavoriteList(userId, videoId) }

    suspend fun addToFavorite(userId: String?, videoId: String?) =
         api.addToFavorite(userId, videoId)

    suspend fun removeFavorite(userId: String?, videoId: String?) =
         api.removeFromFavorite(userId, videoId)

}