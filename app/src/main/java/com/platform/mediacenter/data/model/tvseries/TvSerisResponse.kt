package com.platform.mediacenter.data.model.tvseries


import com.google.gson.annotations.SerializedName

class TvSerisResponse : ArrayList<TvSerisResponse.TvSerisResponseItem>(){
    data class TvSerisResponseItem(
        @SerializedName("ad_link")
        val adLink: String?,
        @SerializedName("description")
        val description: String?, // مردی که همه تبهکاران از او می‌ترسیدند و تمام قاتلان حرفه‌ای او را تحسین می‌کردند، روزی عاشق شد. وقتی ساکاموتو با آئوی، کارمند فروشگاه، آشنا شد، عشق از نگاه اول اتفاق افتاد و درست در همان لحظه، او بازنشسته شد، تا اینکه...
        @SerializedName("imdb_rating")
        val imdbRating: String?, // 8.2
        @SerializedName("is_paid")
        val isPaid: String?, // 1
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/poster_image/5709.jpg
        @SerializedName("release")
        val release: String?, // 2025
        @SerializedName("runtime")
        val runtime: String?, // 50 دقیقه
        @SerializedName("slug")
        val slug: String?, // sakamoto-days
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/video_thumb/5709.jpg
        @SerializedName("title")
        val title: String?, // Sakamoto Days
        @SerializedName("trailer")
        val trailer: String?, // https://play.cinemart.info/1403/Sakamoto.Days.S01.Trailer.mp4
        @SerializedName("video_descadd")
        val videoDescadd: String?, // زیرنویس چسبیده
        @SerializedName("video_quality")
        val videoQuality: String?, // HD
        @SerializedName("videos_id")
        val videosId: String? // 5709
    )
}