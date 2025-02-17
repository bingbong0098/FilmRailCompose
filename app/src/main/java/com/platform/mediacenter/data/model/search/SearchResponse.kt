package com.platform.mediacenter.data.model.search


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("movie")
    val movie: List<Movie?>?,
    @SerializedName("tv_channels")
    val tvChannels: List<TvChannel?>?,
    @SerializedName("tvseries")
    val tvseries: List<Tvsery?>?
) {
    data class Movie(
        @SerializedName("ad_link")
        val adLink: String?, // https://s3.distam.site/dl/Tizer/main%20ADJ.mp4
        @SerializedName("description")
        val description: String?, // خلاصه داستان: فیلم درباره زندگی «مالنا» (مونیکا بلوچی) است. وی دختر زیبارویی است که به تازگی به همراهی پدرش، به یک شهر کوچک نقل مکان کرده. زیبایی مالنا به گونه ای است که مردم شهر در مورد وی شایعه پردازی می کنند. شایعه هایی که بالاخره باعث می شود که پدرش از او روی گردان شود تا اینکه ...
        @SerializedName("imdb_rating")
        val imdbRating: String?, // 7.3
        @SerializedName("is_tvseries")
        val isTvseries: String?, // 0
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/poster_image/565.jpg
        @SerializedName("release")
        val release: String?, // 2000
        @SerializedName("runtime")
        val runtime: String?, // 109 دقیقه
        @SerializedName("slug")
        val slug: String?, // malena
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/video_thumb/565.jpg
        @SerializedName("title")
        val title: String?, // Malena
        @SerializedName("trailer")
        val trailer: String?, // https://s3.distam.site/dl/Trailer/Trailer-Malena.2000.mp4
        @SerializedName("video_descadd")
        val videoDescadd: String?, // زیرنویس چسبیده
        @SerializedName("video_quality")
        val videoQuality: String?, // HD
        @SerializedName("videos_id")
        val videosId: String? // 565
    )

    data class TvChannel(
        @SerializedName("description")
        val description: String?, // شبکه Newsmax 2 منبعی قابل اعتماد برای پخش زنده اخبار و تحلیل‌های دقیق از رویدادهای جهانی است.
        @SerializedName("live_tv_id")
        val liveTvId: String?, // 1542
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/tv_image/newsmax-2.webp
        @SerializedName("slug")
        val slug: String?, // newsmax-2
        @SerializedName("stream_from")
        val streamFrom: String?, // hls
        @SerializedName("stream_label")
        val streamLabel: String?, // HD
        @SerializedName("stream_url")
        val streamUrl: String?, // https://newsmax-samsungus.amagi.tv/playlist.m3u8
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/tv_image/sm/newsmax-2.webp
        @SerializedName("tv_name")
        val tvName: String? // Newsmax 2
    )

    data class Tvsery(
        @SerializedName("ad_link")
        val adLink: String?, // https://s3.distam.site/dl/Tizer/main%20ADJ.mp4
        @SerializedName("description")
        val description: String?, // خلاصه داستان: این یک بازی نیست ، بلکه هزینه نبوغ است. نگاهی عمیق تر به چگونگی حیات بخشیدن این سریال تحسین برانگیز توسط اسکات فرانک ، آنیا تیلور-جوی و تیم پشتیبان The Queen's Gambit بیندازید...
        @SerializedName("imdb_rating")
        val imdbRating: String?, // 8.7
        @SerializedName("is_tvseries")
        val isTvseries: String?, // 1
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/poster_image/387.jpg
        @SerializedName("release")
        val release: String?, // 2020-10-23
        @SerializedName("runtime")
        val runtime: String?, // 55 دقیقه
        @SerializedName("slug")
        val slug: String?, // the-queens-gambit
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/video_thumb/387.jpg
        @SerializedName("title")
        val title: String?, // The Queen's Gambit
        @SerializedName("trailer")
        val trailer: String?, // https://play.cinemart.info/1399/760656683.mp4
        @SerializedName("video_descadd")
        val videoDescadd: String?, // دوبله
        @SerializedName("video_quality")
        val videoQuality: String?, // HD
        @SerializedName("videos_id")
        val videosId: String? // 387
    )
}