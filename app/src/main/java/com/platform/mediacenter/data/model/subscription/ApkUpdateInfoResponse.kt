package com.platform.mediacenter.data.model.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApkUpdateInfoResponse(
    @SerializedName("version_code")
    @Expose
    var versionCode: String? = null,

    @SerializedName("version_name")
    @Expose
    var versionName: String? = null,

    @SerializedName("whats_new")
    @Expose
    var whatsNew: String? = null,

    @SerializedName("apk_url")
    @Expose
    var apkUrl: String? = null,

    @SerializedName("is_skipable")
    @Expose
    var isSkipable: Boolean = false

)
