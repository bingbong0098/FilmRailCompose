package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.search.SearchResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: SearchRepository) : ViewModel() {

    val searchContent = MutableStateFlow<NetworkResult<SearchResponse?>>(NetworkResult.Loading())


    fun getSearchData(
        query: String?,
        type: String?,
        rangeTo: Int?,
        rangeFrom: Int?,
        countryId: Int?,
        genreId: Int?,
        tvCategoryId: Int?,
        rangeimdbfrom: Int?,
        rangeimdbto: String?
    ){
        viewModelScope.launch {
            searchContent.emit(
                repository.getSearchData(
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
            )

        }
    }
}