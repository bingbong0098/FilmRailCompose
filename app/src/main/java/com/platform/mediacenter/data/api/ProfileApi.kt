package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.BaseResponse
import com.platform.mediacenter.data.model.login.UserResponse
import com.platform.mediacenter.data.model.login.VerifyCodeResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ProfileApi {


    @FormUrlEncoded
    @POST("check_existing_user")
    suspend fun checkUserExistOrNot(@Field("term") term: String?): Response<BaseResponse?>


    @FormUrlEncoded
    @POST("login")
    suspend fun postLoginStatus(
        @Field("email") email: String?,
        @Field("user_agent") userAgent: String?,
        @Field("password") password: String?
    ): Response<UserResponse?>

    @FormUrlEncoded
    @POST("login_with_phone")
    suspend fun postPhoneLoginStatus(
        @Field("phone") phone: String?,
        @Field("user_agent") userAgent: String?,
        @Field("password") password: String?
    ): Response<UserResponse?>

    @GET("check_login_valid")
    suspend fun checkLoginValid(): Response<BaseResponse?>


    @FormUrlEncoded
    @POST("send_phone_verify_code")
    suspend fun verifyCode(
        @Field("phone") phone: String?
    ): Response<VerifyCodeResponse?>

    @FormUrlEncoded
    @POST("signup")
    suspend fun signUp(
        @Field("email") email: String?,
        @Field("user_agent") userAgent: String?,
        @Field("password") password: String?,
        @Field("name") name: String?
    ): Response<UserResponse?>

    @FormUrlEncoded
    @POST("signup_with_phone")
    suspend fun signUpWithPhone(
        @Field("phone") phone: String?,
        @Field("user_agent") userAgent: String?,
        @Field("password") password: String?,
        @Field("name") name: String?,
        @Field("verify_code") verifyCode: String?
    ): Response<UserResponse?>


    @FormUrlEncoded
    @POST("password_reset")
    suspend fun resetPassword(
        @Field("email") email: String?
    ): Response<BaseResponse?>

    @FormUrlEncoded
    @POST("password_reset_with_phone")
    suspend fun resetPasswordPhone(
        @Field("phone") phone: String?,
        @Field("verify_code") code: String?
    ): Response<BaseResponse?>


    @Multipart
    @POST("update_profile")
    suspend fun updateProfile(
        @Part("id") id: RequestBody?,
        @Part("name") name: RequestBody?,
        @Part("email") email: RequestBody?,
        @Part("phone") phone: RequestBody?,
        @Part("password") password: RequestBody?,
        @Part("current_password") currentPassword: RequestBody?,
        @Part photo: MultipartBody.Part?,
        @Part("gender") gender: RequestBody?
    ): Response<BaseResponse?>


    @GET("user_details_by_user_id")
    suspend fun getUserData(
        @Query("id") userId: String?
    ): Response<UserResponse?>


    @POST("deactivate_account")
    @FormUrlEncoded
    suspend fun deactivateAccount(
        @Field("id") id: String?,
        @Field("password") password: String?,
        @Field("reason") reason: String?
    ): Response<BaseResponse?>

}