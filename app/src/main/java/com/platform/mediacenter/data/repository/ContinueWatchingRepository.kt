package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.ContinueWatchingApi
import com.platform.mediacenter.data.remote.BaseApiResponse
import javax.inject.Inject

class ContinueWatchingRepository @Inject constructor(private val api: ContinueWatchingApi) :
    BaseApiResponse() {
    suspend fun getAllWatchedHistory(page: Int) = safeApiCall { api.getAllWatchedHistory(page) }
}