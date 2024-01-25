package com.example.bestsurfspots.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bestsurfspots.R
import com.example.bestsurfspots.models.Spot
import com.example.bestsurfspots.utils.MyApi
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class AddSpotFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_spot, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val addSpotButton = view.findViewById<Button>(R.id.add_spot_page_button_submit)

        addSpotButton.setOnClickListener {
            // Handle button click here
            handleButtonClick()
        }
    }

    private fun handleButtonClick() {
        // récupérer les inputs
        val destination = view?.findViewById<EditText>(R.id.add_spot_page_input_destination)
        val address = view?.findViewById<EditText>(R.id.add_spot_page_input_address)
        val photoURL = view?.findViewById<EditText>(R.id.add_spot_page_input_photoURL)

        // les mettre dans un objet Spot en format json
        val spot = Spot(
            destination?.text.toString(),
            photoURL?.text.toString(),
            address?.text.toString()
        )

        val json = Gson().toJson(spot)

        // faire une requete post avec retrofit
        addSpot(json)

        // afficher un toast pour dire que le spot a été ajouté
        Toast.makeText(context, "Votre spot a bien été ajouté !", Toast.LENGTH_SHORT).show()
    }

    private fun addSpot(json: String) {
        val api = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)

        // Ajoutez le header "Content-Type: application/json"
        val requestBody = RequestBody.create(MediaType.parse("application/json"), json)

        api.addSpot(requestBody).enqueue(object : Callback<Spot> {
            override fun onResponse(call: Call<Spot>, response: Response<Spot>) {
                if (response.isSuccessful) {
                    Log.d("AddSpotFragment", "Spot added")
                } else {
                    Log.d("AddSpotFragment", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Spot>, t: Throwable) {
                Log.d("AddSpotFragment", "Error: ${t.message}")
            }
        })
    }
}