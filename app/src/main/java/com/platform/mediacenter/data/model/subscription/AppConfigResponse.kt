package com.platform.mediacenter.data.model.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AppConfigResponse(
    @SerializedName("menu")
    @Expose
    var menu: String? = null,

    @SerializedName("program_guide_enable")
    @Expose
    var programGuideEnable: Boolean? = null,

    @SerializedName("mandatory_login")
    @Expose
    var mandatoryLogin: Boolean? = null,

    @SerializedName("genre_visible")
    @Expose
    var genreVisible: Boolean? = null,

    @SerializedName("country_visible")
    @Expose
    var countryVisible: Boolean? = null,

    @SerializedName("terms")
    @Expose
    var terms: String? = null
)
