package com.platform.mediacenter.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("status")
    @Expose
    val status: String? = null,

    @SerializedName("data")
    @Expose
    val data: String? = null,

    @SerializedName("message")
    @Expose
    val message: String? = null
)
