package com.platform.mediacenter.data.repository


import com.platform.mediacenter.data.api.GenreApi
import com.platform.mediacenter.data.model.genre.GenreResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class GenreRepository  @Inject constructor(private val api : GenreApi): BaseApiResponse(){
    suspend fun getGenre(): NetworkResult<GenreResponse> = safeApiCall { api.getGenre() }

}