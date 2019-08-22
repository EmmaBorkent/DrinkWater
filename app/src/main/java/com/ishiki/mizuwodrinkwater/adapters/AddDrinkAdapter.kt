package com.ishiki.mizuwodrinkwater.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks

class AddDrinkAdapter(private val glassesList: ArrayList<Drinks>, private val context: Context) :
    RecyclerView.Adapter<AddDrinkAdapter.DrinkHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.add_drink_item, parent, false)
        return DrinkHolder(view)
    }

    override fun getItemCount(): Int {
        return glassesList.size
    }

    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
        holder.bindViews(glassesList[position])
    }

    inner class DrinkHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.add_drink_list_image)
        private val volume = itemView.findViewById<TextView>(R.id.add_drink_list_volume)

        fun bindViews(drinks: Drinks) {
            val resourceId = context.resources.getIdentifier(drinks.image, "drawable", context.packageName)
            image.setImageResource(resourceId)
            volume.text = drinks.volume.toString()
        }
    }

}