package com.ishiki.mizuwodrinkwater.adapters

import android.annotation.SuppressLint
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

    @SuppressLint("InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val drinksView: View
        val holder: ViewHolder

        if (convertView == null) {
            drinksView = LayoutInflater.from(context).inflate(R.layout.today_drinks_list, null)
            holder = ViewHolder()
            holder.glassImage = drinksView.findViewById(R.id.drinkListImage)
            holder.glassVolume = drinksView.findViewById(R.id.drinkListText)
            drinksView.tag = holder
        } else {
            holder = convertView.tag as ViewHolder
            drinksView = convertView
        }

        val drink = drinksToday[position]
        val resourceId = context.resources.getIdentifier("water02", "drawable", context.packageName)
        holder.glassImage?.setImageResource(resourceId)
        holder.glassVolume?.text = drink.volume
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

    private class ViewHolder {
        var glassImage: ImageView? = null
        var glassVolume: TextView? = null
    }
}