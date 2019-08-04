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

class GlassesAdapter(private val glassesList: ArrayList<Drinks>, private val context: Context) :
    RecyclerView.Adapter<GlassesAdapter.GlassHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.glasses_list, parent, false)
        return GlassHolder(view)
    }

    override fun getItemCount(): Int {
        return glassesList.size
    }

    override fun onBindViewHolder(holder: GlassHolder, position: Int) {
        holder.bindViews(glassesList[position])
    }


    inner class GlassHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById(R.id.glasses_list_image) as ImageView
        private val volume = itemView.findViewById(R.id.glasses_list_volume) as TextView

        fun bindViews(drinks: Drinks) {
            val resourceId = context.resources.getIdentifier(drinks.image, "drawable", context.packageName)
            image.setImageResource(resourceId)
            volume.text = drinks.volume.toString()
        }

    }

}