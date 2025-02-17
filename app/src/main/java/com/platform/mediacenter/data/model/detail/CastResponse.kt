package com.platform.mediacenter.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CastResponse(
    @SerializedName("star_id")
    @Expose
    val starId: String,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("url")
    @Expose
    val url: String,

    @SerializedName("image_url")
    @Expose
    val imageUrl: String
)
