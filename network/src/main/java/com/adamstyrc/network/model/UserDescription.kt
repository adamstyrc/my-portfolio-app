package com.adamstyrc.network.model

import com.google.gson.annotations.SerializedName

data class UserDescription(
    @SerializedName("name") val name: String?,
    @SerializedName("avatar") val avatar: String?,
    @SerializedName("skills") val skills: ArrayList<String>?,
    @SerializedName("description") val description: String?
)