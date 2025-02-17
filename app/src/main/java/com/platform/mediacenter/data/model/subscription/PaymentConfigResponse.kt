package com.platform.mediacenter.data.model.subscription

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PaymentConfigResponse(

    @SerializedName("currency_symbol")
    @Expose
    var currencySymbol: String? = null,

    @SerializedName("currency")
    @Expose
    var currency: String? = null,

    @SerializedName("exchnage_rate")
    @Expose
    var exchangeRate: String? = null,

    @SerializedName("paypal_enable")
    @Expose
    var paypalEnable: Boolean? = null,

    @SerializedName("paypal_email")
    @Expose
    var paypalEmail: String? = null,

    @SerializedName("paypal_client_id")
    @Expose
    var paypalClientId: String? = null,

    @SerializedName("stripe_enable")
    @Expose
    var stripeEnable: Boolean? = null,

    @SerializedName("stripe_publishable_key")
    @Expose
    var stripePublishableKey: String? = null,

    @SerializedName("stripe_secret_key")
    @Expose
    var stripeSecretKey: String? = null,

    @SerializedName("razorpay_enable")
    @Expose
    var razorpayEnable: Boolean? = null,

    @SerializedName("razorpay_key_id")
    @Expose
    var razorpayKeyId: String? = null,

    @SerializedName("razorpay_key_secret")
    @Expose
    var razorpayKeySecret: String? = null,

    @SerializedName("razorpay_inr_exchange_rate")
    @Expose
    var razorpayExchangeRate: String? = null,

    @SerializedName("offline_payment_enable")
    @Expose
    var offlinePaymentEnable: Boolean = false,

    @SerializedName("offline_payment_title")
    @Expose
    var offlinePaymentTitle: String? = null,

    @SerializedName("offline_payment_instruction")
    @Expose
    var offlinePaymentInstruction: String? = null

)
