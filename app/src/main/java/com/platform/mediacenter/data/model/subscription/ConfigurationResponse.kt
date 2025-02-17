package com.platform.mediacenter.data.model.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.platform.mediacenter.data.model.country.CountryResponse
import com.platform.mediacenter.data.model.genre.GenreResponse

data class ConfigurationResponse(
    @SerializedName("id")
    @Expose
    var id: Int = 0,

    @SerializedName("app_config")
    @Expose
    var appConfig: AppConfigResponse? = null,

    @SerializedName("ads_config")
    @Expose
    var adsConfig: AdsConfigResponse? = null,

    @SerializedName("payment_config")
    @Expose
    var paymentConfig: PaymentConfigResponse? = null,

    @SerializedName("genre")
    @Expose
    var genre: List<GenreResponse>? = null,

    @SerializedName("country")
    @Expose
    var country: List<CountryResponse>? = null,

    @SerializedName("tv_category")
    @Expose
    var tvCategory: List<TvCategoryResponse>? = null,

    @SerializedName("apk_version_info")
    @Expose
    var apkUpdateInfo: ApkUpdateInfoResponse? = null

)
