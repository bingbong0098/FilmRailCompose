package com.platform.mediacenter.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("elapsed_time")
    @Expose
    val elapsedTime: String,

    @SerializedName("video_file_id")
    @Expose
    val videoFileId: String,

    @SerializedName("label")
    @Expose
    val label: String,

    @SerializedName("stream_key")
    @Expose
    val streamKey: String,

    @SerializedName("file_type")
    @Expose
    val fileType: String,

    @SerializedName("file_url")
    @Expose
    val fileUrl: String,

    @SerializedName("subtitle")
    @Expose
    val subtitle: List<SubtitleResponse>? = null,

    @SerializedName("titration_end_at")
    @Expose
    val titrationEndAt: String
)
