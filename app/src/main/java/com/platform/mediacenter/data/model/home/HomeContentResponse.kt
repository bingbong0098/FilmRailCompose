package com.platform.mediacenter.data.model.home


import com.google.gson.annotations.SerializedName

data class HomeContentResponse(
    @SerializedName("all_country")
    val allCountry: List<AllCountry?>?,
    @SerializedName("all_genre")
    val allGenre: List<AllGenre?>?,
    @SerializedName("featured_tv_channel")
    val featuredTvChannel: List<FeaturedTvChannel?>?,
    @SerializedName("features_genre_and_movie")
    val featuresGenreAndMovie: List<FeaturesGenreAndMovie?>?,
    @SerializedName("latest_movies")
    val latestMovies: List<LatestMovy?>?,
    @SerializedName("latest_tvseries")
    val latestTvseries: List<LatestTvsery?>?,
    @SerializedName("popular_stars")
    val popularStars: List<PopularStar?>?,
    @SerializedName("slider")
    val slider: Slider?,
    @SerializedName("watched_history")
    val watchedHistory: List<WatchedHistory?>?
) {
    data class AllCountry(
        @SerializedName("country_id")
        val countryId: String?, // 3
        @SerializedName("description")
        val description: String?,
        @SerializedName("image_url")
        val imageUrl: String?, // https://filmrail.xyz/app/uploads/country/3.png
        @SerializedName("name")
        val name: String?, // USA
        @SerializedName("slug")
        val slug: String?, // usa
        @SerializedName("url")
        val url: String? // https://filmrail.xyz/app/country/usa.html
    )

    data class AllGenre(
        @SerializedName("description")
        val description: String?,
        @SerializedName("genre_id")
        val genreId: String?, // 3
        @SerializedName("image_url")
        val imageUrl: String?, // https://filmrail.xyz/app/uploads/genre/3.png
        @SerializedName("name")
        val name: String?, // اکشن
        @SerializedName("slug")
        val slug: String?, // action
        @SerializedName("url")
        val url: String? // https://filmrail.xyz/app/genre/action.html
    )

    data class FeaturedTvChannel(
        @SerializedName("description")
        val description: String?, // خلاصه بازی منچسترسیتی و وستهم در چارچوب هفته 20 لیگ برتر انگلیس فصل 2024/25
        @SerializedName("is_paid")
        val isPaid: String?, // 1
        @SerializedName("live_tv_id")
        val liveTvId: String?, // 1674
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/tv_image/manchester-city-4-west-ham-1-match-summary.jpg
        @SerializedName("slug")
        val slug: String?, // manchester-city-4-west-ham-1-match-summary
        @SerializedName("stream_from")
        val streamFrom: String?, // hls
        @SerializedName("stream_label")
        val streamLabel: String?, // HD
        @SerializedName("stream_url")
        val streamUrl: String?, // https://r1-vod.varzesh3.com/vod/363933/5/index-f1-v1-a1.m3u8
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/tv_image/sm/manchester-city-4-west-ham-1-match-summary.jpg
        @SerializedName("tv_name")
        val tvName: String? // خلاصه بازی منچسترسیتی 4 - وستهم 1
    )

    data class FeaturesGenreAndMovie(
        @SerializedName("description")
        val description: String?, // Animation Movies
        @SerializedName("genre_id")
        val genreId: String?, // 5
        @SerializedName("name")
        val name: String?, // انیمیشن
        @SerializedName("slug")
        val slug: String?, // انیمیشن
        @SerializedName("url")
        val url: String?, // https://filmrail.xyz/app/genre/انیمیشن.html
        @SerializedName("videos")
        val videos: List<Video?>?
    ) {
        data class Video(
            @SerializedName("ad_link")
            val adLink: String?,
            @SerializedName("description")
            val description: String?, // گرومیت نگران است که والاس بیش از حد به اختراعاتش وابسته شده است، که این نگرانی زمانی ثابت می‌شود که والاس یک "گنوم هوشمند" اختراع می‌کند که به نظر می‌رسد ذهن خودش را پیدا می‌کند.
            @SerializedName("imdb_rating")
            val imdbRating: String?, // 7.9
            @SerializedName("is_paid")
            val isPaid: String?, // 1
            @SerializedName("is_tvseries")
            val isTvseries: String?, // 0
            @SerializedName("poster_url")
            val posterUrl: String?, // https://filmrail.xyz/app/uploads/poster_image/5589.jpg
            @SerializedName("release")
            val release: String?, // 2024
            @SerializedName("runtime")
            val runtime: String?, // 79 دقیقه
            @SerializedName("slug")
            val slug: String?, // wallace-gromit-vergeltung-mit-flgeln
            @SerializedName("thumbnail_url")
            val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/video_thumb/5589.jpg
            @SerializedName("title")
            val title: String?, // Wallace & Gromit: Vergeltung mit Flügeln
            @SerializedName("trailer")
            val trailer: String?, // https://play.cinemart.info/1403/Wallace.and.Gromit.Vengeance.Most.Fowl.2024.Official.Trailer.mp4
            @SerializedName("video_descadd")
            val videoDescadd: String?, // زیرنویس چسبیده
            @SerializedName("video_quality")
            val videoQuality: String?, // HD
            @SerializedName("videos_id")
            val videosId: String? // 5589
        )
    }

    data class LatestMovy(
        @SerializedName("ad_link")
        val adLink: String?,
        @SerializedName("description")
        val description: String?, // داستان مورت را دنبال می‌کند که متوجه می‌شود کمتر از یک سال دیگر عمر دارد. پس از اینکه نامزدش او را ترک می‌کند، با کیت در یک سرویس دوستیابی آشنا می‌شود که افراد را بر اساس تاریخ مرگشان با هم مطابقت می‌دهد. در تمام این مدت، او توسط یک روسپی آشفته تعقیب می‌شود.
        @SerializedName("imdb_rating")
        val imdbRating: String?, // 4.8
        @SerializedName("is_paid")
        val isPaid: String?, // 1
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/poster_image/4613.jpg
        @SerializedName("release")
        val release: String?, // 2024
        @SerializedName("runtime")
        val runtime: String?, // 91 دقیقه
        @SerializedName("slug")
        val slug: String?, // running-on-empty
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/video_thumb/4613.jpg
        @SerializedName("title")
        val title: String?, // Running on Empty
        @SerializedName("trailer")
        val trailer: String?, // https://play.cinemart.info/1403/Running.on.Empty.2024.mp4
        @SerializedName("video_descadd")
        val videoDescadd: String?, // زیرنویس چسبیده
        @SerializedName("video_quality")
        val videoQuality: String?, // HD
        @SerializedName("videos_id")
        val videosId: String? // 4613
    )

    data class LatestTvsery(
        @SerializedName("ad_link")
        val adLink: String?,
        @SerializedName("description")
        val description: String?, // «آژانس» داستان یک مأمور مخفی سیا به نام «مارتین» (با بازی مایکل فاسبندر) را روایت می‌کند که پس از سال‌ها زندگی زیر پوشش، به لندن فراخوانده می‌شود. با بازگشت او، عشق قدیمی‌اش نیز ظاهر می‌شود و این امر او را در موقعیتی دشوار بین وظیفه و احساسات قرار می‌دهد.
        @SerializedName("imdb_rating")
        val imdbRating: String?, // 7.3
        @SerializedName("is_paid")
        val isPaid: String?, // 1
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/poster_image/5413.jpg
        @SerializedName("release")
        val release: String?, // 2024
        @SerializedName("runtime")
        val runtime: String?, // 50 دقیقه
        @SerializedName("slug")
        val slug: String?, // the-agency
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/video_thumb/5413.jpg
        @SerializedName("title")
        val title: String?, // The Agency
        @SerializedName("trailer")
        val trailer: String?, // https://play.cinemart.info/1403/The.Agency.S01.Trailer.mp4
        @SerializedName("video_descadd")
        val videoDescadd: String?, // زیرنویس چسبیده
        @SerializedName("video_quality")
        val videoQuality: String?, // HD
        @SerializedName("videos_id")
        val videosId: String? // 5413
    )

    data class WatchedHistory(
        @SerializedName("description")
        val description: String?, // «آژانس» داستان یک مأمور مخفی سیا به نام «مارتین» (با بازی مایکل فاسبندر) را روایت می‌کند که پس از سال‌ها زندگی زیر پوشش، به لندن فراخوانده می‌شود. با بازگشت او، عشق قدیمی‌اش نیز ظاهر می‌شود و این امر او را در موقعیتی دشوار بین وظیفه و احساسات قرار می‌دهد.
        @SerializedName("imdb_rating")
        val imdbRating: String?, // 7.3
        @SerializedName("is_paid")
        val isPaid: String?, // 1
        @SerializedName("poster_url")
        val posterUrl: String?, // https://filmrail.xyz/app/uploads/poster_image/5413.jpg
        @SerializedName("release")
        val release: String?, // 2024
        @SerializedName("runtime")
        val runtime: String?, // 50 دقیقه
        @SerializedName("slug")
        val slug: String?, // the-agency
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/video_thumb/5413.jpg
        @SerializedName("title")
        val title: String?, // The Agency
        @SerializedName("trailer")
        val trailer: String?, // https://play.cinemart.info/1403/The.Agency.S01.Trailer.mp4
        @SerializedName("video_descadd")
        val videoDescadd: String?, // زیرنویس چسبیده
        @SerializedName("video_quality")
        val videoQuality: String?, // HD
        @SerializedName("stream_label")
        val streamLabel: String? = null,
        @SerializedName("stream_from")
        val streamFrom: String? = null,
        @SerializedName("is_tvseries")
        val isTvseries: String? = null,
        @SerializedName("elapsed_time")
        val elapsedTime: String? = null,
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("total_time")
        val totalTime: String? = null,
        @SerializedName("stream_url")
        val streamUrl: String? = null
    )

    data class PopularStar(
        @SerializedName("image_url")
        val imageUrl: String?, // https://filmrail.xyz/app/uploads/star_image/211.jpg
        @SerializedName("star_id")
        val starId: String?, // 211
        @SerializedName("star_name")
        val starName: String? // بهروز وثوقی
    )

    data class Slider(
        @SerializedName("slide")
        val slide: List<Slide?>?,
        @SerializedName("slider_type")
        val sliderType: String? // movie
    ) {
        data class Slide(
            @SerializedName("action_btn_text")
            val actionBtnText: String?, // Play
            @SerializedName("action_id")
            val actionId: String?, // 4613
            @SerializedName("action_type")
            val actionType: String?, // movie
            @SerializedName("action_url")
            val actionUrl: String?,
            @SerializedName("description")
            val description: String?, // داستان مورت را دنبال می‌کند که متوجه می‌شود کمتر از یک سال دیگر عمر دارد. پس از اینکه نامزدش او را ترک می‌کند، با کیت در یک سرویس دوستیابی آشنا می‌شود که افراد را بر اساس تاریخ مرگشان با هم مطابقت می‌دهد. در تمام این مدت، او توسط یک روسپی آشفته تعقیب می‌شود.
            @SerializedName("id")
            val id: String?, // 4613
            @SerializedName("image_link")
            val imageLink: String?, // https://filmrail.xyz/app/uploads/poster_image/4613.jpg
            @SerializedName("slug")
            val slug: String?, // running-on-empty
            @SerializedName("title")
            val title: String?, // Running on Empty
            @SerializedName("trailer")
            val trailer: String? // https://play.cinemart.info/1403/Running.on.Empty.2024.mp4
        )
    }
}