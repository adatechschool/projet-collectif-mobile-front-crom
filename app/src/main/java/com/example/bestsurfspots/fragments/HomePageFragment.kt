package com.example.bestsurfspots.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.delasign.samplestarterproject.utils.ReadJSONFromAssets
import com.example.bestsurfspots.MainActivity
import com.example.bestsurfspots.R
import com.example.bestsurfspots.adapter.SpotAdapter
import com.example.bestsurfspots.models.SpotsModel
import com.example.bestsurfspots.utils.MyApi
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePageFragment (
    private val context : MainActivity
): Fragment(
) {
    private var spotList: List<SpotsModel.Spot> = emptyList()  // Initialize as an empty list
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)

        // Update the spotList with the response from the API
        getAllSpots()

        //recuperer le recyclerview de SpotAdapter
        val homepageRecyclerView = view.findViewById<RecyclerView>(R.id.homepage_recycler_list)
        homepageRecyclerView.adapter = SpotAdapter(context, spotList, R.layout.item_spot_list)

        return view
    }

    private fun getAllSpots() {
        val api = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getSpots().enqueue(object : Callback<SpotsModel> {
            override fun onResponse(call: Call<SpotsModel>, response: Response<SpotsModel>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        spotList = it.records  // Update spotList with the response
                        // Notify the adapter that the data has changed
                        updateAdapter()
                    }
                }
            }

            override fun onFailure(call: Call<SpotsModel>, t: Throwable) {
                Log.i("Spot", "Error: ${t.message}")
            }
        })
    }

    private fun updateAdapter() {
        // Update the RecyclerView adapter with the new data
        val homepageRecyclerView = view?.findViewById<RecyclerView>(R.id.homepage_recycler_list)
        homepageRecyclerView?.adapter = SpotAdapter(context, spotList, R.layout.item_spot_list)
    }
}