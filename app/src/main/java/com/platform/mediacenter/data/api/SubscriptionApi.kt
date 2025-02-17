package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.subscription.ActiveStatusResponse
import com.platform.mediacenter.data.model.subscription.ConfigurationResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface SubscriptionApi {

    @GET("check_user_subscription_status")
    suspend fun getActiveStatus(
        @Query("user_id") userId: String?
    ): Response<ActiveStatusResponse?>

//    @GET("subscription_history")
//    fun getSubscriptionHistory(
//        @Query("user_id") userId: String?
//    ): Response<SubscriptionHistory?>

    @GET("cancel_subscription")
    suspend fun cancelSubscription(
        @Query("user_id") userId: String?,
        @Query("subscription_id") subscriptionId: String?
    ): Response<ResponseBody?>

    @GET("config")
    suspend fun getConfigurationData(): Response<ConfigurationResponse?>
}