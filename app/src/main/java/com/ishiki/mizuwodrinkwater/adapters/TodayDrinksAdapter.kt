package com.ishiki.mizuwodrinkwater.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks

class TodayDrinksAdapter(val context: Context, val drinksToday: MutableList<Drinks>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val drinksView: View

        drinksView = LayoutInflater.from(context).inflate(R.layout.fragment_glass, null)
        val glassImage: ImageView = drinksView.findViewById(R.id.fragmentGlassImage)
        val glassVolume: TextView = drinksView.findViewById(R.id.fragmentVolumeText)

        val drink = drinksToday[position]
        // Change 250 ml String to reference in Drinks model when using different volumes
        glassVolume.text = "250 ml"

        // Change water02.png String to the image in the Drinks model when using different volumes
        val resourceId = context.resources.getIdentifier("water02", "drawable", context.packageName)
        glassImage.setImageResource(resourceId)
        println(resourceId)

        return drinksView
    }

    override fun getItem(position: Int): Any {
        return drinksToday[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return drinksToday.count()
    }
}