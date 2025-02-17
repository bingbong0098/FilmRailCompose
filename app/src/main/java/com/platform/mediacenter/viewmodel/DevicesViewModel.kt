package com.platform.mediacenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.platform.mediacenter.data.model.devices.DeviceResponse
import com.platform.mediacenter.data.remote.NetworkResult
import com.platform.mediacenter.data.repository.DevicesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevicesViewModel @Inject constructor(private val repository : DevicesRepository):ViewModel() {

    val allDevices = MutableStateFlow<NetworkResult<DeviceResponse?>>(NetworkResult.Loading())

    fun getAllDevices(){
        viewModelScope.launch {
            allDevices.emit(repository.getAllDevices())
        }
    }
}