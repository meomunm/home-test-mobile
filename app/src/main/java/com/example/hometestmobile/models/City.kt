package com.example.hometestmobile.models

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("Name") var name: String?,
    @SerializedName("CityCode") var cityCode: Int?
)