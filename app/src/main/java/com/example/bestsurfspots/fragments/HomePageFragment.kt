package com.example.bestsurfspots.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.delasign.samplestarterproject.utils.ReadJSONFromAssets
import com.example.bestsurfspots.MainActivity
import com.example.bestsurfspots.R
import com.example.bestsurfspots.models.SpotModel
import com.example.bestsurfspots.adapter.SpotAdapter
import com.example.bestsurfspots.models.UIContent
import com.google.gson.Gson

class HomePageFragment (
    private val context : MainActivity
): Fragment(
) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)

        //lire les données au format JSON
        val JSONString = ReadJSONFromAssets(context, "sample.json")
        val data = Gson().fromJson(JSONString, UIContent::class.java)


        //fonction pour parser les données JSON et créer une liste de spots


        val spotList = data.records


        //recuperer le recyclerview de SpotAdapter
        val homepageRecyclerView = view.findViewById<RecyclerView>(R.id.homepage_recycler_list)
        homepageRecyclerView.adapter = SpotAdapter(context, spotList, R.layout.item_spot_list)

        return view
    }


}