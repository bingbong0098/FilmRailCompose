package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.LiveTvResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.LiveTvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LiveTvViewModel @Inject constructor(private val repository: LiveTvRepository) : ViewModel() {

    val liveTvContent = MutableStateFlow<NetworkResult<LiveTvResponse>>(NetworkResult.Loading())


    suspend fun getLiveTvContent() {
        viewModelScope.launch {
            liveTvContent.emit(repository.getLiveTv())

        }
    }

}