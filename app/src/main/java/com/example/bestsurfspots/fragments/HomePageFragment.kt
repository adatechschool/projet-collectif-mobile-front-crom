package com.example.bestsurfspots.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bestsurfspots.MainActivity
import com.example.bestsurfspots.R
import com.example.bestsurfspots.SpotModel
import com.example.bestsurfspots.adapter.SpotAdapter

class HomePageFragment (
    private val context : MainActivity
): Fragment(
) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)
        val spotList = arrayListOf<SpotModel>()

        //ajouter des spots
        spotList.add(SpotModel())
        spotList.add(SpotModel("Pipeline", "https://www.surf-forecast.com/system/images/4295/large/Banzai-Pipelines-and-Backdoor.jpg?1324521720", "Hawaii" ))
        spotList.add(SpotModel("Superbank", "https://andyoucreations.com/wp-content/uploads/2016/12/Banzai-pipeline.jpg", "Australie"))

        //recuperer le recyclerview de SpotAdapter
        val homepageRecyclerView = view.findViewById<RecyclerView>(R.id.homepage_recycler_list)
        homepageRecyclerView.adapter = SpotAdapter(context, spotList, R.layout.item_horizontal_spot)

        return view
    }
}