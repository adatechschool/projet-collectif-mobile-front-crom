package com.example.bestsurfspots.models

data class UIContent (val records: List<Spot>){
    data class Spot (
        val spotName : String,
        val spotImage : String,
        val spotLocation : String
    )
}