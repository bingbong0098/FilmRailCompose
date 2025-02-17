package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.SearchApi
import com.platform.mediacenter.data.model.search.SearchResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class SearchRepository @Inject constructor(private val api: SearchApi) : BaseApiResponse() {
    suspend fun getSearchData(
        query: String?,
        type: String?,
        rangeTo: Int?,
        rangeFrom: Int?,
        countryId: Int?,
        genreId: Int?,
        tvCategoryId: Int?,
        rangeimdbfrom: Int?,
        rangeimdbto: String?
    ): NetworkResult<SearchResponse?> =
        safeApiCall {
            api.getSearchData(
                query = query,
                type = type,
                rangeTo = rangeTo,
                rangeFrom = rangeFrom,
                rangeimdbfrom = rangeimdbfrom,
                rangeimdbto = rangeimdbto,
                countryId = countryId,
                genreId = genreId,
                tvCategoryId = tvCategoryId
            )
        }

}