package com.platform.mediacenter.data.model.genre


import com.google.gson.annotations.SerializedName

class GenreResponse : ArrayList<GenreResponse.GenreResponseItem>(){
    data class GenreResponseItem(
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
}