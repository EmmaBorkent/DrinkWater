package com.ishiki.mizuwodrinkwater.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Glasses
import com.ishiki.mizuwodrinkwater.services.OnItemClickListenerAddDrinkAdapter

class AddDrinkAdapter(
    private val glassesList: ArrayList<Glasses>,
    private val context: Context,
    private val listener: OnItemClickListenerAddDrinkAdapter
) : RecyclerView.Adapter<AddDrinkAdapter.DrinkHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.add_drink_item, parent, false)
        return DrinkHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return glassesList.size
    }

    override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
        holder.bindViews(glassesList[position])
    }

    inner class DrinkHolder(itemView: View, private val listener: OnItemClickListenerAddDrinkAdapter) :
        RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.add_drink_list_image)
        private val volume = itemView.findViewById<TextView>(R.id.add_drink_list_volume)

        fun bindViews(glass: Glasses) {
            val resourceId = context.resources.getIdentifier(glass.image, "drawable", context.packageName)
            image.setImageResource(resourceId)
            volume.text = glass.volume.toString()
            listener.onItemClickedAddDrink(glass, adapterPosition)
        }
    }

}