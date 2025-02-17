package com.platform.mediacenter.data.model.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ActiveStatusResponse(
    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("package_title")
    @Expose
    var packageTitle: String? = null,

    @SerializedName("expire_date")
    @Expose
    var expireDate: String? = null,

    var expireTime: Long = 0
)