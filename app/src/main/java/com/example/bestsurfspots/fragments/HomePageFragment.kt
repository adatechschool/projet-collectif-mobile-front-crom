package com.example.bestsurfspots.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bestsurfspots.R
import com.example.bestsurfspots.adapter.SpotAdapter

class HomePageFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_homepage, container, false)

        //recuperer le recyclerview de SpotAdapter
        val homepageRecyclerView = view.findViewById<RecyclerView>(R.id.homepage_recycler_list)
        homepageRecyclerView.adapter = SpotAdapter()

        return view
    }
}