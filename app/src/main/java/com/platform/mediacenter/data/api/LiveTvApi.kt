package com.platform.mediacenter.data.api


import com.platform.mediacenter.data.model.LiveTvResponse
import retrofit2.Response
import retrofit2.http.GET

interface LiveTvApi {

    @GET("all_tv_channel_by_category")
    suspend fun getLiveTv(): Response<LiveTvResponse>


}