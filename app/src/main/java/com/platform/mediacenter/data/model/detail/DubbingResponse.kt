package com.platform.mediacenter.data.model.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DubbingResponse(
    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("studio")
    @Expose
    val studio: String,

    @SerializedName("dubbing_manager")
    @Expose
    val dubbingManagers: List<DirectorResponse>,

    @SerializedName("translator")
    @Expose
    val translators: List<DirectorResponse>,

    @SerializedName("voice_recordist")
    @Expose
    val voiceRecordists: List<DirectorResponse>,

    @SerializedName("speakers")
    @Expose
    val speakers: List<DirectorResponse>
)
