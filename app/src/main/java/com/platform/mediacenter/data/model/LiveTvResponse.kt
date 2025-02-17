package com.platform.mediacenter.data.model


import com.google.gson.annotations.SerializedName

class LiveTvResponse : ArrayList<LiveTvResponse.LiveTvResponseItem>(){
    data class LiveTvResponseItem(
        @SerializedName("channels")
        val channels: List<Channel?>?,
        @SerializedName("description")
        val description: String?, // کوپا آمریکا 2024
        @SerializedName("live_tv_category_id")
        val liveTvCategoryId: String?, // 19
        @SerializedName("title")
        val title: String? // جام ملت های آمریکا ۲۰۲۴
    ) {
        data class Channel(
            @SerializedName("description")
            val description: String?, // خلاصه بازی آرژانتین - کلمبیا در دیدار فینال رقابت‌های کوپا آمریکا 2024
            @SerializedName("is_paid")
            val isPaid: String?, // 1
            @SerializedName("live_tv_id")
            val liveTvId: String?, // 821
            @SerializedName("poster_url")
            val posterUrl: String?, // https://filmrail.xyz/app/uploads/tv_image/1-0-f3b5aq7wenky.jpg
            @SerializedName("slug")
            val slug: String?, // 1-0-f3b5aq7wenky
            @SerializedName("stream_from")
            val streamFrom: String?, // hls
            @SerializedName("stream_label")
            val streamLabel: String?, // HD
            @SerializedName("stream_url")
            val streamUrl: String?, // https://r1-vod.varzesh3.com/vod/347571/9/index-f1-v1-a1.m3u8
            @SerializedName("thumbnail_url")
            val thumbnailUrl: String?, // https://filmrail.xyz/app/uploads/tv_image/sm/1-0-f3b5aq7wenky.jpg
            @SerializedName("tv_name")
            val tvName: String? // خلاصه بازی آرژانتین 1 - 0 کلمبیا
        )
    }
}