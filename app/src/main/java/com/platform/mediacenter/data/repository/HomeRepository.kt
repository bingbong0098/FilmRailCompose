package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.HomeApi
import com.platform.mediacenter.data.model.home.HomeContentResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApi) : BaseApiResponse() {

    suspend fun homeContent(): NetworkResult<HomeContentResponse> =
        safeApiCall { api.homeContent() }

}