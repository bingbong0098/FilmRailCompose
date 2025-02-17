package com.platform.mediacenter.data.model.login


import com.google.gson.annotations.SerializedName

data class VerifyCodeResponse(
    @SerializedName("data")
    val `data`: String?, // کد تایید با موفقیت ارسال شد.
    @SerializedName("status")
    val status: String?, // success
    @SerializedName("time_remaining")
    val timeRemaining: Int? // 180
)