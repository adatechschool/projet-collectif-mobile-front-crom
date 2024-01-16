package com.example.bestsurfspots.utils

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import com.example.bestsurfspots.models.SpotsModel

interface MyApi {
    @GET("spots")  // Replace with the actual endpoint path
    fun getSpots() : Call<SpotsModel>
}
