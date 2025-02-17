package com.platform.mediacenter.data.api

import com.platform.mediacenter.data.model.BaseResponse
import com.platform.mediacenter.data.model.comment.CommentsResponse
import com.platform.mediacenter.data.model.detail.SingleDetailsResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface DetailApi {

    @GET("single_details")
    suspend fun getSingleDetails(
        @Query("type") type: String?,
        @Query("id") id: String?
    ): Response<SingleDetailsResponse?>

    @GET("add_favorite")
    suspend fun addToFavorite(
        @Query("user_id") userId: String?,
        @Query("videos_id") videoId: String?
    ): Response<BaseResponse?>

    @GET("remove_favorite")
    suspend fun removeFromFavorite(
        @Query("user_id") userId: String?,
        @Query("videos_id") videoId: String?
    ): Response<BaseResponse?>

    @GET("verify_favorite_list")
    suspend fun verifyFavoriteList(
        @Query("user_id") userId: String?,
        @Query("videos_id") videoId: String?
    ): Response<BaseResponse?>


    @GET("all_comments")
    suspend fun getAllComments(@Query("id") id: String?): Response<List<CommentsResponse?>?>

//    @FormUrlEncoded
//    @POST("comments")
//    suspend fun postComment(
//        @Field("videos_id") videoId: String?,
//        @Field("user_id") userId: String?,
//        @Field("comment") comment: String?
//    ): Response<PostCommentModel?>?
//
//    @FormUrlEncoded
//    @POST("add_replay")
//    suspend fun postReply(
//        @Field("videos_id") videoId: String?,
//        @Field("user_id") userId: String?,
//        @Field("comment") comment: String?,
//        @Field("comments_id") commentId: String?
//    ): Response<PostCommentModel?>?
//
//    @GET("all_replay")
//    suspend fun getAllReply(@Query("id") id: String?): Response<List<GetCommentsModel?>?>?

}