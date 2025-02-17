package com.platform.mediacenter.data.repository

import com.platform.mediacenter.data.api.ProfileApi
import com.platform.mediacenter.data.model.BaseResponse
import com.platform.mediacenter.data.model.login.UserResponse
import com.platform.mediacenter.data.model.login.VerifyCodeResponse
import com.platform.mediacenter.data.remote.BaseApiResponse
import com.platform.mediacenter.data.remote.NetworkResult
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ProfileApi) : BaseApiResponse() {

    suspend fun checkLoginValid(): Response<BaseResponse?> =
         api.checkLoginValid()

    suspend fun getUserData(userId: String?): NetworkResult<UserResponse?> =
        safeApiCall { api.getUserData(userId) }

    suspend fun deactivateAccount(
        userId: String?,
        password: String?,
        reason: String?
    ): NetworkResult<BaseResponse?> =
        safeApiCall { api.deactivateAccount(userId, password, reason) }

    suspend fun updateProfile(
        userId: RequestBody?,
        name: RequestBody?,
        email: RequestBody?,
        phone: RequestBody?,
        password: RequestBody?,
        currentPassword: RequestBody?,
        photo: MultipartBody.Part?,
        gender: RequestBody?,
    ): NetworkResult<BaseResponse?> =
        safeApiCall {
            api.updateProfile(
                userId,
                name,
                email,
                phone,
                password,
                currentPassword,
                photo,
                gender
            )
        }

    suspend fun resetPasswordPhone(
        phone: String?,
        verifyCode: String?
    ): NetworkResult<BaseResponse?> =
        safeApiCall { api.resetPasswordPhone(phone, verifyCode) }

    suspend fun resetPassword(email: String?): NetworkResult<BaseResponse?> =
        safeApiCall { api.resetPassword(email) }

    suspend fun checkUserExistOrNot(email: String?): NetworkResult<BaseResponse?> =
        safeApiCall { api.checkUserExistOrNot(email) }

    suspend fun loginEmail(
        email: String?,
        userAgent: String?,
        password: String?
    ): NetworkResult<UserResponse?> =
        safeApiCall { api.postLoginStatus(email, userAgent, password) }

    suspend fun loginPhone(
        phone: String?,
        userAgent: String?,
        password: String?
    ): NetworkResult<UserResponse?> =
        safeApiCall { api.postPhoneLoginStatus(phone, userAgent, password) }

    suspend fun signUpEmail(
        email: String?,
        userAgent: String?,
        password: String?,
        name: String?
    ): NetworkResult<UserResponse?> =
        safeApiCall { api.signUp(email, userAgent, password, name) }

    suspend fun signUpPhone(
        phone: String?,
        userAgent: String?,
        password: String?,
        name: String?,
        verifyCode: String?
    ): NetworkResult<UserResponse?> =
        safeApiCall { api.signUpWithPhone(phone, userAgent, password, name, verifyCode) }


    suspend fun verifyCode(phone: String?): NetworkResult<VerifyCodeResponse?> =
        safeApiCall { api.verifyCode(phone) }


}