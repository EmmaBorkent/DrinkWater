package com.ishiki.mizuwodrinkwater.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DataService.drinksToday

class TodayDrinksRecyclerAdapter(private val context: Context, private val todaysDrinks: MutableList<Drinks>) :
    RecyclerView.Adapter<TodayDrinksRecyclerAdapter.Holder>() {

//    interface OnDeleteListener {
//        fun deleteItem(drinks: Drinks)
//    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.today_drinks_list, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return todaysDrinks.count()
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bindDrinks(todaysDrinks[p1], context)
//        p0.itemView.setOnClickListener {
//            deleteListener.deleteItem(todaysDrinks[p1])
//        }
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val drinkImage = itemView.findViewById<ImageView>(R.id.drinkListImage)
        private val drinkVolume = itemView.findViewById<TextView>(R.id.drinkListText)
        private val drinkUnit = itemView.findViewById<TextView>(R.id.drinkListUnit)
        private val deleteButton = itemView.findViewById<ImageButton>(R.id.drinkListButtonDelete)

        fun bindDrinks(drinks: Drinks, context: Context) {
            val resourceId = context.resources.getIdentifier(drinks.image, "drawable", context.packageName)
            drinkImage.setImageResource(resourceId)
            drinkVolume.text = drinks.volume
            drinkUnit.text = drinks.unit
            deleteButton.setOnClickListener {
                val position = adapterPosition
                println("Delete Me at $position")
                drinksToday.removeAt(position)
                notifyDataSetChanged()

//                deleteItem(position)

//                val position = adapterPosition
//                deleteItem(position)
            }
        }

//        fun deleteItem(drinks: Drinks) {
//            drinksToday.remove(drinks)
//        }

//        private fun deleteItem(position: Int) {
//            drinksToday.removeAt(position)
//        }
    }
}
