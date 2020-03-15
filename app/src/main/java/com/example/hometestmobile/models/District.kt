package com.example.hometestmobile.models

import com.google.gson.annotations.SerializedName

data class District(
    @SerializedName("CityCode") var cityCode: Int?,
    @SerializedName("Name") var name: String?,
    @SerializedName("DistrictCode") var districtCode: Int?
)