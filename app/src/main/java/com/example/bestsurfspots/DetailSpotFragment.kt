package com.example.bestsurfspots

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.bestsurfspots.adapter.SpotAdapter
import com.example.bestsurfspots.models.SpotsModel

class DetailSpotFragment (
    private val adapter: SpotAdapter,
    private val currentSpot: SpotsModel.Spot
) : Dialog (adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.fragment_spot_detail)
        setupComponents()
    }

    private fun setupComponents() {
        // actualiser les informations du spot
        val spotImage = findViewById<ImageView>(R.id.spot_image)
        Glide.with(adapter.context).load(Uri.parse(currentSpot.fields.Photos[0].url)).into(spotImage)
        val spotName = findViewById<TextView>(R.id.spot_name)
        spotName.text = currentSpot.fields.Destination
        val spotLocation = findViewById<TextView>(R.id.spot_location)
        spotLocation.text = currentSpot.fields.Address
    }


}