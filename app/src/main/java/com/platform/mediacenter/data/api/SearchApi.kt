package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search")
    suspend fun getSearchData(
        @Query("q") query: String?,
        @Query("type") type: String?,
        @Query("range_to") rangeTo: Int?,
        @Query("range_from") rangeFrom: Int?,
        @Query("tv_category_id") tvCategoryId: Int?,
        @Query("genre_id") genreId: Int?,
        @Query("country_id") countryId: Int?,
        @Query("range_imdb_to") rangeimdbto: String?,
        @Query("range_imdb_from") rangeimdbfrom: Int?
    ): Response<SearchResponse?>

}