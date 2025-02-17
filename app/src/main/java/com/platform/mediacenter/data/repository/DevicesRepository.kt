package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.DeviceManagerApi
import com.platform.mediacenter.data.model.devices.DeviceResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import javax.inject.Inject

class DevicesRepository  @Inject constructor(private val api: DeviceManagerApi) : BaseApiResponse() {
    suspend fun getAllDevices(): NetworkResult<DeviceResponse?> =
        safeApiCall { api.getAllDevices() }
}