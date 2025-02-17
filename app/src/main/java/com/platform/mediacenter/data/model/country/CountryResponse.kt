package com.platform.mediacenter.data.model.country


import com.google.gson.annotations.SerializedName

class CountryResponse : ArrayList<CountryResponse.CountryResponseItem>(){
    data class CountryResponseItem(
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
}