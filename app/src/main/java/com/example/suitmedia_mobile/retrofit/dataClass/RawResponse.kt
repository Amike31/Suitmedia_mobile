package com.example.suitmedia_mobile.retrofit.dataClass

import com.google.gson.annotations.SerializedName

data class RawResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("per_page") val per_page: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("data") val data: List<UserData>,
    @SerializedName("support") val support: SupportData
    )
