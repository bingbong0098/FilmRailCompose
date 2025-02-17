package com.platform.mediacenter.data.model.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("status")
    @Expose
    var status: String?,

    @SerializedName("user_id")
    @Expose
    val userId: String?,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("email")
    @Expose
    val email: String?,

    @SerializedName("phone")
    @Expose
    val phone: String?,

    @SerializedName("gender")
    @Expose
    val gender: String?,

    @SerializedName("join_date")
    @Expose
    val joinDate: String?,

    @SerializedName("last_login")
    @Expose
    val lastLogin: String?,

    @SerializedName("data")
    @Expose
    val data: String?,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String?,

    @SerializedName("access_token")
    @Expose
    val accessToken: String?,

    @SerializedName("password_available")
    @Expose
    val isPasswordAvailable: Boolean,

)
