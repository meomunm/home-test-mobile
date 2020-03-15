package com.example.hometestmobile.models

import com.google.gson.annotations.SerializedName

data class Cities(
    @SerializedName("Cities") var cities: List<City>
)