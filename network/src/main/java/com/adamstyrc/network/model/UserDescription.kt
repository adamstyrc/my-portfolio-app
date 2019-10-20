package com.adamstyrc.network.model

import com.google.gson.annotations.SerializedName

data class UserDescription(
    @SerializedName("name") val name: String?,
    @SerializedName("skills") val skills: List<String>?,
    @SerializedName("description") val description: String?
)