package com.example.suitmedia_mobile.retrofit.dataClass

import com.google.gson.annotations.SerializedName

data class SupportData(
    @SerializedName("url") val url: String,
    @SerializedName("text") val text: String
)
