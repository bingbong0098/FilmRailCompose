package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.home.HomeContentResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("home_content_for_android")
    suspend fun homeContent(): Response<HomeContentResponse>
}