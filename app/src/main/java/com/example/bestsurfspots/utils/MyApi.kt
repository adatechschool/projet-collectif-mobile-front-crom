package com.example.bestsurfspots.utils
/*

import com.example.bestsurfspots.models.SpotsModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request


fun ReadJSONFromAPI(): SpotsModel? {
    // Création du client OkHttp
    val client = OkHttpClient()

    // Création de la requête avec l'URL et les en-têtes
    val request = Request.Builder()
        .url("https://api.airtable.com/v0/appOt5G2A02GYVh0r/tbl1DNSMskeBaQoZ1")
        .addHeader(
            "Authorization",
            "Bearer patH1YQltqqqBPsOb.c75f90282c37da83216cf1992d314275fc51bead328370a1e01a880eba479f22"
        )
        .build()

    // Exécution de la requête
    val response = client.newCall(request).execute()

    // Traitement de la réponse
    if (response.isSuccessful) {
        val responseBody = response.body?.string()
        // Faites quelque chose avec le corps de la réponse (responseBody)
        println("API Response: $responseBody")
        // Création d'une instance de Gson
        val gson = Gson()
        // Conversion du JSON en objet Kotlin
        val data = gson.fromJson(responseBody, SpotsModel::class.java)
        return data
    } else {
        // Gestion des cas où la réponse n'est pas réussie
        println("API Call Failed with response code: ${response.code}")
        return null
    }
}
*/

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import com.example.bestsurfspots.models.SpotsModel

interface MyApi {
    @GET("appOt5G2A02GYVh0r/tbl1DNSMskeBaQoZ1/")  // Replace with the actual endpoint path
    fun getSpots(@Header("Authorization") authorization: String): Call<SpotsModel>
}
