package com.example.hometestmobile.models

import com.google.gson.annotations.SerializedName

data class Districts(
    @SerializedName("Districts") var districts: List<District>
)