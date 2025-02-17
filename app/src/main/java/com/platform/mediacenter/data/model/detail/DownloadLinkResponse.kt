package com.platform.mediacenter.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DownloadLinkResponse(
    @SerializedName("download_link_id")
    @Expose
    val downloadLinkId: String,

    @SerializedName("label")
    @Expose
    val label: String,

    @SerializedName("videos_id")
    @Expose
    val videosId: String,

    @SerializedName("resolution")
    @Expose
    val resolution: String,

    @SerializedName("file_size")
    @Expose
    val fileSize: String,

    @SerializedName("download_url")
    @Expose
    val downloadUrl: String,

    @SerializedName("in_app_download")
    @Expose
    val isInAppDownload: Boolean,

    var downloadFileName: String? = null
)
