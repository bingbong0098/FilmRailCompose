package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.devices.DeviceResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface DeviceManagerApi {
    @GET("get_devices")
    suspend fun getAllDevices(): Response<DeviceResponse?>

    @GET("remove_all_other_devices")
    suspend fun removeAllOtherDevices(): Response<DeviceResponse?>

    @FormUrlEncoded
    @POST("remove_device")
    suspend fun removeDevice(
        @Field("id") id: String?,
        @Field("type") type: String?
    ): Response<DeviceResponse?>

}