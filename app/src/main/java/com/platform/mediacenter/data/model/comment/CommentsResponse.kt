package com.platform.mediacenter.data.model.comment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CommentsResponse (
    @SerializedName("comments_id")
    @Expose
    val commentsId: String,

    @SerializedName("videos_id")
    @Expose
    val videosId: String,

    @SerializedName("user_id")
    @Expose
    val userId: String,

    @SerializedName("user_name")
    @Expose
    val userName: String,

    @SerializedName("user_img_url")
    @Expose
    val userImgUrl: String,

    @SerializedName("comments")
    @Expose
    val comments: String,

    @SerializedName("replay_id")
    @Expose
    val replyId: String

)