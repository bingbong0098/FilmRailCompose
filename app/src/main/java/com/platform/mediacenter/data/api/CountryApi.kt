package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.country.CountryResponse
import com.platform.mediacenter.data.model.genre.GenreResponse
import com.platform.mediacenter.data.model.home.HomeContentResponse
import retrofit2.Response
import retrofit2.http.GET

interface CountryApi {

    @GET("all_country")
    suspend fun getAllCountry(): Response<CountryResponse>
}