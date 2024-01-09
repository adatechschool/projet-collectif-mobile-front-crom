package com.example.bestsurfspots.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bestsurfspots.R

class SpotAdapter : RecyclerView.Adapter<SpotAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val spotImage = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Gestion de la vue : va implémenter le layout que l'on a créé dans item_horizontal_spot
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_horizontal_spot, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        // Nombre d'items que la page va afficher.
        return 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // La fonction va mettre à jour le modèle en fonction du spot
    }
}