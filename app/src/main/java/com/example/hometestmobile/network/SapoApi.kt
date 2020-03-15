package com.example.hometestmobile.network

import com.example.hometestmobile.models.Cities
import com.example.hometestmobile.models.Districts
import io.reactivex.Single
import retrofit2.http.GET

interface SapoApi {
    @GET("sapo-tech/home_test_mobile/master/Cities.json")
    fun listCity(): Single<Cities>

    @GET("sapo-tech/home_test_mobile/master/Districts.json")
    fun listDistrict(): Single<Districts>
}