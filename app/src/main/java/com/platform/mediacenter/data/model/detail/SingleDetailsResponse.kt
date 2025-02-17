package com.platform.mediacenter.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SingleDetailsResponse(

    @SerializedName("user_rating")
    @Expose
    val userRating: String,

    @SerializedName("total_rating")
    @Expose
    val totalRating: String,

    @SerializedName("rating")
    @Expose
    val rating: String,

    @SerializedName("videos_id")
    @Expose
    val videosId: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("secondary_title")
    @Expose
    val secondaryTitle: String,

    @SerializedName("description")
    @Expose
    val description: String,

    @SerializedName("awards")
    @Expose
    val awards: String?,

    @SerializedName("imdb_rank")
    @Expose
    val imdbRank: String?,

    @SerializedName("dubbings")
    @Expose
    val dubbings: ArrayList<DubbingResponse>?,

    @SerializedName("slug")
    @Expose
    val slug: String,

    @SerializedName("release")
    @Expose
    val release: String?,

    @SerializedName("runtime")
    @Expose
    val runtime: String,

    @SerializedName("video_quality")
    @Expose
    val videoQuality: String,

    @SerializedName("imdb_rating")
    @Expose
    val imdbRating: String?,

    @SerializedName("video_descadd")
    @Expose
    val videoDescadd: String,

    @SerializedName("is_tvseries")
    @Expose
    val isTvseries: String,

    @SerializedName("is_paid")
    @Expose
    val isPaid: String,

    @SerializedName("enable_download")
    @Expose
    val enableDownload: String,

    @SerializedName("enable_single_buy")
    @Expose
    val enableSingleBuy: String,

    @SerializedName("download_links")
    @Expose
    val downloadLinks: List<DownloadLinkResponse>?,

    @SerializedName("thumbnail_url")
    @Expose
    val thumbnailUrl: String,

    @SerializedName("poster_url")
    @Expose
    val posterUrl: String,

    @SerializedName("videos")
    @Expose
    val videos: List<VideoResponse>?,

    @SerializedName("genre")
    @Expose
    val genre: List<GenreDetailResponse>?,

    @SerializedName("country")
    @Expose
    val country: List<CountryDetailResponse>?,

    @SerializedName("director")
    @Expose
    val director: List<DirectorResponse>?,

    @SerializedName("writer")
    @Expose
    val writer: List<WriterResponse>?,

    @SerializedName("cast")
    @Expose
    val cast: List<CastResponse>?,

    @SerializedName("cast_and_crew")
    @Expose
    val castAndCrew: List<CastAndCrewResponse>?,

    @SerializedName("related_movie")
    @Expose
    val relatedMovie: List<RelatedMovieResponse>?,

    @SerializedName("season")
    @Expose
    val season: List<SeasonResponse>?,

    @SerializedName("related_tvseries")
    @Expose
    val relatedTvseries: List<RelatedMovieResponse>?,

    @SerializedName("trailler_youtube_source")
    @Expose
    val trailerUrl: String?,

    val adLink: String?,

    val broadcastDay: String?
)
