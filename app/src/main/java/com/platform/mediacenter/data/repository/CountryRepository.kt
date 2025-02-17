package com.platform.mediacenter.data.repository


import com.platform.mediacenter.data.api.CountryApi
import com.platform.mediacenter.data.model.country.CountryResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class CountryRepository  @Inject constructor(private val api : CountryApi): BaseApiResponse(){
    suspend fun getAllCountry(): NetworkResult<CountryResponse> = safeApiCall { api.getAllCountry() }

}