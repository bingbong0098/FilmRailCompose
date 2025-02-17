package com.platform.mediacenter.data.model.watchedhistory

import java.io.Serializable

data class WatchedHistoryResponse(

    val streamLabel: String?,
    val release: String?,
    val videoQuality: String?,
    val description: String?,
    val runtime: String?,
    val posterUrl: String?,
    val title: String?,
    val thumbnailUrl: String?,
    val trailer: String?,
    val streamFrom: String?,
    val videoDescadd: String?,
    val isTvSeries: String?,
    val elapsedTime: String?,
    val id: String?,
    val isPaid: String?,
    val imdbRating: String?,
    val totalTime: String?,
    val slug: String?,
    val streamUrl: String?

) : Serializable
