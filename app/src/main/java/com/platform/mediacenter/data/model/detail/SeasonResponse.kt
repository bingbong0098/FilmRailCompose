package com.platform.mediacenter.data.model.detail


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SeasonResponse(
    @SerializedName("seasons_id")
    @Expose
    val seasonsId: String,

    @SerializedName("seasons_name")
    @Expose
    val seasonsName: String,

    @SerializedName("episodes")
    @Expose
    val episodes: List<EpisodeResponse>?,

    @SerializedName("enable_download")
    @Expose
    val enableDownload: String,

    @SerializedName("download_links")
    @Expose
    val downloadLinks: List<DownloadLinkResponse>?
)
