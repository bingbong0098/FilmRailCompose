package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.tvseries.TvSerisResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.TvSeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvSeriesViewModel @Inject constructor(private val repository: TvSeriesRepository) : ViewModel() {
    val tvSeriesContent = MutableStateFlow<NetworkResult<TvSerisResponse>>(NetworkResult.Loading())


    fun getTvSeriesContent(page : Int) {
        viewModelScope.launch {
            tvSeriesContent.emit(repository.getSeries(page))

        }
    }

}