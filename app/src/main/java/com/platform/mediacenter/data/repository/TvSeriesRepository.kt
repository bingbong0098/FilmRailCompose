package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.TvSerisApi
import com.platform.mediacenter.data.model.tvseries.TvSerisResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class TvSeriesRepository @Inject constructor(private val api : TvSerisApi): BaseApiResponse(){
    suspend fun getSeries(page : Int): NetworkResult<TvSerisResponse> = safeApiCall { api.getSeries(page) }

}