package com.ishiki.mizuwodrinkwater.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
//import android.widget.TextView
import com.ishiki.mizuwodrinkwater.R
//import com.ishiki.mizuwodrinkwater.controller.OnDeleteListener
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksToday
import com.ishiki.mizuwodrinkwater.services.DrinksToday.dailyTotal
import kotlinx.android.synthetic.main.today_drinks_list.view.*

class TodayDrinksRecyclerAdapter(private val context: Context, private val todayDrinks: MutableList<Drinks>,
                                 private var onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<TodayDrinksRecyclerAdapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.today_drinks_list, p0, false)
//        this.itemClickListener = itemClickListener
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return todayDrinks.count()
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bindDrinks(todayDrinks[p1])

//        if (null != OnItemClickListener) {
//            itemClickListener.onItemClick(TodayDrinksRecyclerAdapter)
//        }
//        p0.bindDrinks(todayDrinks[p1], context)
//        if (null != callback) callback.onItemClick(dailyTotalTextView)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bindDrinks(drinks: Drinks, context: Context, callback: OnItemClickListener)
        fun bindDrinks(drinks: Drinks) {
            val resourceId = context.resources.getIdentifier(drinks.image, "drawable", context.packageName)
            itemView.drinkListImage.setImageResource(resourceId)
            itemView.drinkListText.text = drinks.volume
            itemView.drinkListUnit.text = drinks.unit

            itemView.drinkListButtonDelete.setOnClickListener {
                val position = adapterPosition
                DrinksToday.removeDrink(position)
                dailyTotal -= drinks.volume.toInt()
//                val dailyTotalTextView = itemView.findViewById<TextView>(R.id.mainTextDailyTotal)
//                dailyTotalTextView.text = dailyTotal.toString()

                notifyDataSetChanged()
                onItemClickListener.onItemClick(dailyTotal)

                // Print to check
                println("Delete Me at $position")
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(dailyTotal: Int)
    }
}
