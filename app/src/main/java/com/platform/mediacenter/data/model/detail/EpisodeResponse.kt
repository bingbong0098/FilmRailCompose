package com.platform.mediacenter.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EpisodeResponse(

    @SerializedName("episodes_id")
    @Expose
    val episodesId: String,

    @SerializedName("episodes_name")
    @Expose
    val episodesName: String,

    @SerializedName("stream_key")
    @Expose
    val streamKey: String,

    @SerializedName("titration_end_at")
    @Expose
    val titrationEndAt: String,

    @SerializedName("elapsed_time")
    @Expose
    val elapsedTime: String,

    @SerializedName("total_time")
    @Expose
    val totalTime: String,

    @SerializedName("file_type")
    @Expose
    val fileType: String,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String,

    @SerializedName("file_url")
    @Expose
    val fileUrl: String,

    @SerializedName("subtitle")
    @Expose
    val subtitle: List<SubtitleResponse>?
)
