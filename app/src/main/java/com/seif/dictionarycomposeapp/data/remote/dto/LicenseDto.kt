package com.seif.dictionarycomposeapp.data.remote.dto


import com.google.gson.annotations.SerializedName

data class LicenseDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)