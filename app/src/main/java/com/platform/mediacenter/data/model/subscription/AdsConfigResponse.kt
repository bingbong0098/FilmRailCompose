package com.platform.mediacenter.data.model.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AdsConfigResponse (
    @SerializedName("ads_enable")
    @Expose
    var adsEnable: String? = null,

    @SerializedName("mobile_ads_network")
    @Expose
    var mobileAdsNetwork: String? = null,

    @SerializedName("admob_app_id")
    @Expose
    var admobAppId: String? = null,

    @SerializedName("admob_banner_ads_id")
    @Expose
    var admobBannerAdsId: String? = null,

    @SerializedName("admob_interstitial_ads_id")
    @Expose
    var admobInterstitialAdsId: String? = null,

    @SerializedName("admob_native_ads_id")
    @Expose
    var admobNativeAdsId: String? = null,

    @SerializedName("fan_native_ads_placement_id")
    @Expose
    var fanNativeAdsPlacementId: String? = null,

    @SerializedName("fan_banner_ads_placement_id")
    @Expose
    var fanBannerAdsPlacementId: String? = null,

    @SerializedName("fan_interstitial_ads_placement_id")
    @Expose
    var fanInterstitialAdsPlacementId: String? = null,

    @SerializedName("startapp_app_id")
    @Expose
    var startappAppId: String? = null,

    @SerializedName("startapp_developer_id")
    @Expose
    var startappDeveloperId: String? = null

)