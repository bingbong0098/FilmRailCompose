package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.home.HomeContentResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val homeContent = MutableStateFlow<NetworkResult<HomeContentResponse>>(NetworkResult.Loading())

    suspend fun getHomeContent() {
        viewModelScope.launch {
            homeContent.emit(repository.homeContent())

        }
    }


}