package com.example.bestsurfspots.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.delasign.samplestarterproject.utils.ReadJSONFromAssets
import com.example.bestsurfspots.MainActivity
import com.example.bestsurfspots.R
import com.example.bestsurfspots.adapter.SpotAdapter
import com.example.bestsurfspots.models.Spot
import com.example.bestsurfspots.utils.MyApi
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomePageFragment(private val context: MainActivity) : Fragment() {
    private var spotList: List<Spot> = emptyList()
    private lateinit var rootView: View  // Property to store the root view

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_homepage, container, false)

        // Use lifecycleScope.launch to perform the asynchronous operation
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            // Update the spotList with the response from the API
            getAllSpots()

            // Update the RecyclerView adapter with the new data on the main thread
            launch(Dispatchers.Main) {
                updateAdapter()
            }
        }

        return rootView
    }

    private fun getAllSpots() {
        val api = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        api.getSpots().enqueue(object : Callback<List<Spot>> {
            override fun onResponse(call: Call<List<Spot>>, response: Response<List<Spot>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        spotList = it  // Update spotList with the response
                        updateAdapter()
                    }
                }
            }

            override fun onFailure(call: Call<List<Spot>>, t: Throwable) {
                Log.i("Spot", "Error: ${t.message}")
            }
        })
    }

    private fun updateAdapter() {
        // Update the RecyclerView adapter with the new data
        val homepageRecyclerView = rootView.findViewById<RecyclerView>(R.id.homepage_recycler_list)
        val adapter = SpotAdapter(context, spotList, R.layout.item_spot_list)
        homepageRecyclerView.adapter = adapter
    }
}