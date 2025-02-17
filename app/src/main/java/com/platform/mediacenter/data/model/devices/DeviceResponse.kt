package com.platform.mediacenter.data.model.devices

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DeviceResponse(
    val data: Data?,
    val message: String?,
    val status: String?
) : Serializable {

    data class Data(
        @SerializedName("max_devices") val maxDevices: String?,
        val devices: List<Device>?
    ) : Serializable

    data class Device(
        @SerializedName("current_device") val isCurrentDevice: Boolean?,
        @SerializedName("device_name") val deviceName: String?,
        val id: String?,
        @SerializedName("ip_address") val ipAddress: String?,
        val type: String?
    ) : Serializable
}
