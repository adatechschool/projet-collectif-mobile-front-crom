package com.example.bestsurfspots.adapter
import android.net.Uri
import android.util.Log
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
import com.example.bestsurfspots.models.Spot
import com.example.bestsurfspots.utils.MyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpotAdapter (
    val context: MainActivity,
    private val spotList: List<Spot>,
    private val layout: Int
)
    : RecyclerView.Adapter<SpotAdapter.ViewHolder> () {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val spotImage = view.findViewById<ImageView>(R.id.spot_image)
        val spotName = view.findViewById<TextView>(R.id.spot_name)
        val spotLocation = view.findViewById<TextView>(R.id.spot_location)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
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
        Glide.with(context).load(Uri.parse(currentSpot.PhotoURL)).into(holder.spotImage)
        holder.spotName.text = currentSpot.Destination
        holder.spotLocation.text = currentSpot.Address
        if (currentSpot.Favorites) {
            holder.starIcon.setImageResource(R.drawable.favorites)
        }else{
            holder.starIcon.setImageResource(R.drawable.star)
        }

        holder.starIcon.setOnClickListener{
            val api = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)

            api.putFavorites(currentSpot.ID).enqueue(object : Callback<Spot> {
                override fun onResponse(call: Call<Spot>, response: Response<Spot>) {
                    if (response.isSuccessful) {
                        // Mettez à jour l'icône étoile en conséquence
                        if (currentSpot.Favorites) {
                            holder.starIcon.setImageResource(R.drawable.favorites)
                        } else {
                            holder.starIcon.setImageResource(R.drawable.star)
                        }
                    } else {
                        Log.d("AddFavorites", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<Spot>, t: Throwable) {
                    Log.d("AddFavorites", "Error: ${t.message}")
                }
            })
        }

        // Gestion du clic sur un spot
        holder.itemView.setOnClickListener {
            // Création d'une instance de DetailSpotFragment et affichage
            DetailSpotFragment(this, currentSpot).show()
        }
    }
}