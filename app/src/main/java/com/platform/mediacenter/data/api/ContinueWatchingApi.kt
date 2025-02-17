package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.BaseResponse
import com.platform.mediacenter.data.model.watchedhistory.WatchedHistoryResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ContinueWatchingApi {

    @GET("watched_history")
    suspend fun getAllWatchedHistory(@Query("page") page: Int): Response<List<WatchedHistoryResponse?>?>

    @FormUrlEncoded
    @POST("update_watched_video")
    suspend fun updateContinueWatching(
        @Field("videos_id") videoId: String?,
        @Field("type") type: String?,
        @Field("elapsed_time") elapsedTime: Long,
        @Field("total_time") totalTime: Long,
        @Field("file_id") fileId: String?
    ): Response<BaseResponse?>

}