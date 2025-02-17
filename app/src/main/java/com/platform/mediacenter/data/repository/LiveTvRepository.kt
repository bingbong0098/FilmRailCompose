package com.platform.mediacenter.data.repository


import com.platform.mediacenter.data.api.LiveTvApi
import com.platform.mediacenter.data.model.LiveTvResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class LiveTvRepository  @Inject constructor(private val api : LiveTvApi): BaseApiResponse(){
    suspend fun getLiveTv(): NetworkResult<LiveTvResponse> = safeApiCall { api.getLiveTv() }

}