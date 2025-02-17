package com.platform.mediacenter.data.model.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TvCategoryResponse(
    @SerializedName("live_tv_category_id")
    @Expose
    var liveTvCategoryId: String? = null,

    @SerializedName("live_tv_category")
    @Expose
    var liveTvCategory: String? = null,

    @SerializedName("live_tv_category_desc")
    @Expose
    var liveTvCategoryDesc: String? = null,

    @SerializedName("status")
    @Expose
    var status: String? = null,

    @SerializedName("slug")
    @Expose
    var slug: String? = null

)
