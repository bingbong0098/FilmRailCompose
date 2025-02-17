package com.platform.mediacenter.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RelatedMovieResponse(
    @SerializedName("videos_id")
    @Expose
    val videosId: String,

    @SerializedName("genre")
    @Expose
    val genre: String,

    @SerializedName("country")
    @Expose
    val country: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("slug")
    @Expose
    val slug: String,

    @SerializedName("release")
    @Expose
    val release: String,

    @SerializedName("runtime")
    @Expose
    val runtime: String,

    @SerializedName("video_quality")
    @Expose
    val videoQuality: String,

    @SerializedName("imdb_rating")
    @Expose
    val imdbRating: String,

    @SerializedName("video_descadd")
    @Expose
    val videoDescadd: String,

    @SerializedName("thumbnail_url")
    @Expose
    val thumbnailUrl: String,

    @SerializedName("poster_url")
    @Expose
    val posterUrl: String,

    @SerializedName("is_paid")
    @Expose
    val isPaid: String
)
