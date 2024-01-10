package com.example.bestsurfspots.models

data class SpotsModel (val records: List<Spot>){
    data class Spot (val fields: SpotInformations)
    {
        data class SpotInformations(
            val Destination : String,
            val Photos : List<SpotImage>,
            val Address : String
        )
        {
           data class SpotImage (
               val url : String
           )
        }
    }
}