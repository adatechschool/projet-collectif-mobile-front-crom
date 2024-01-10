package com.example.bestsurfspots.adapter
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bestsurfspots.DetailSpotFragment
import com.example.bestsurfspots.MainActivity
import com.example.bestsurfspots.R
import com.example.bestsurfspots.models.SpotsModel

class SpotAdapter (
    val context: MainActivity,
    private val spotList: List<SpotsModel.Spot>,
    private val layout: Int
)
    : RecyclerView.Adapter<SpotAdapter.ViewHolder> () {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val spotImage = view.findViewById<ImageView>(R.id.spot_image)
        val spotName = view.findViewById<TextView>(R.id.spot_name)
        val spotLocation = view.findViewById<TextView>(R.id.spot_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Gestion de la vue : va implémenter le layout que l'on a créé dans item_spot_list
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_spot_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int
    {
        // Retourne le nombre de spots
        return spotList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // La fonction va mettre à jour le modèle en fonction du spot
        val currentSpot = spotList[position]
        Glide.with(context).load(Uri.parse(currentSpot.fields.Photos[0].url)).into(holder.spotImage)
        holder.spotName.text = currentSpot.fields.Destination
        holder.spotLocation.text = currentSpot.fields.Address

        // Gestion du clic sur un spot
        holder.itemView.setOnClickListener {
            // Création d'une instance de DetailSpotFragment et affichage
            DetailSpotFragment(this, currentSpot).show()
        }
    }
}