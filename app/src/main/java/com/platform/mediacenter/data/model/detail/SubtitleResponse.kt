package com.platform.mediacenter.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SubtitleResponse(
    @SerializedName("subtitle_id")
    @Expose
    val subtitleId: String,

    @SerializedName("videos_id")
    @Expose
    val videosId: String,

    @SerializedName("video_file_id")
    @Expose
    val videoFileId: String,

    @SerializedName("language")
    @Expose
    val language: String,

    @SerializedName("kind")
    @Expose
    val kind: String,

    @SerializedName("url")
    @Expose
    val url: String,

    @SerializedName("lang")
    @Expose
    val lang: String,

    @SerializedName("srclang")
    @Expose
    val srclang: String
)

