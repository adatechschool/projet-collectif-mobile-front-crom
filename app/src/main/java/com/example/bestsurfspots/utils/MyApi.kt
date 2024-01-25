package com.example.bestsurfspots.utils

import com.example.bestsurfspots.models.Spot
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.POST

interface MyApi {
    @GET("spots")  // Replace with the actual endpoint path
    fun getSpots() : Call<List<Spot>>
    @PUT("spot/{id}")
    fun putFavorites(@Path("id") id: String): Call<Spot>
    @GET("favorites")  // Replace with the actual endpoint path
    fun getFavoritesSpots() : Call<List<Spot>>
    @POST("spot")
    fun addSpot(@Body spot: RequestBody) : Call<Spot>
}
