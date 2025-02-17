package com.platform.mediacenter.data.model.movie


import com.google.gson.annotations.SerializedName

class MovieResponse : ArrayList<MovieResponse.MovieResponseItem>(){
    data class MovieResponseItem(
        @SerializedName("ad_link")
        val adLink: String?,
        @SerializedName("description")
        val description: String?, // داستان در مورد یک معدنچی سیارک است که پس از سقوط به سیاره‌ای بیگانه، باید از میان زمین‌های سخت عبور کند، در حالی که اکسیژن او رو به اتمام است و توسط موجودات عجیبی شکار می‌شود، تا به تنها بازمانده دیگر برسد.
        @SerializedName("imdb_rating")
        val imdbRating: String?, // 5.7
        @SerializedName("is_paid")
        val isPaid: String?, // 1
        @SerializedName("is_tvseries")
        val isTvseries: String?, // 0
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/poster_image/5746.jpg
        @SerializedName("release")
        val release: String?, // 2024
        @SerializedName("runtime")
        val runtime: String?, // 87 دقیقه
        @SerializedName("slug")
        val slug: String?, // distant
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/video_thumb/5746.jpg
        @SerializedName("title")
        val title: String?, // Distant
        @SerializedName("trailer")
        val trailer: String?, // https://play.cinemart.info/1403/Distant.2024.Trailer.mp4
        @SerializedName("video_descadd")
        val videoDescadd: String?, // زیرنویس چسبیده
        @SerializedName("video_quality")
        val videoQuality: String?, // HD
        @SerializedName("videos_id")
        val videosId: String? // 5746
    )
}