package com.ishiki.mizuwodrinkwater.adapters

import android.content.Context
import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks

class GlassesAdapter(val context: Context, val drinks: List<Drinks>, val itemClick: (Drinks) -> Unit) : RecyclerView.Adapter<GlassesAdapter.GlassHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GlassHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.set_glass_list, p0, false)
        return GlassHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return drinks.count()
    }

    override fun onBindViewHolder(p0: GlassHolder, p1: Int) {
        p0.bindGlass(drinks[p1], context)
    }

    inner class GlassHolder(itemView: View, val itemClick: (Drinks) -> Unit) : RecyclerView.ViewHolder(itemView) {
        val drinkImage = itemView.findViewById<ImageView>(R.id.setGlassListImage)
        val drinkVolume = itemView.findViewById<TextView>(R.id.setGlassListVolume)
        val drinkUnit = itemView.findViewById<TextView>(R.id.setGlassListUnit)

        fun bindGlass(drink: Drinks, context: Context) {
            val resourceId = context.resources.getIdentifier(drink.image, "drawable", context.packageName)
            drinkImage.setImageResource(resourceId)
            drinkVolume.text = drink.volume
            drinkUnit.text = drink.unit
            itemView.setOnClickListener { itemClick(drink) }
        }
    }
}