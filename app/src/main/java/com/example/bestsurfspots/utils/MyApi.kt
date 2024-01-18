package com.example.bestsurfspots.utils

import com.example.bestsurfspots.models.Spot
import retrofit2.Call
import retrofit2.http.GET

interface MyApi {
    @GET("spots")  // Replace with the actual endpoint path
    fun getSpots() : Call<List<Spot>>
}
