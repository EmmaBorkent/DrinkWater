package com.ishiki.mizuwodrinkwater.unused
//
//import android.content.Context
//import androidx.recyclerview.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.Toast
//import com.ishiki.mizuwodrinkwater.R
//import com.ishiki.mizuwodrinkwater.model.Drinks
//import kotlinx.android.synthetic.main.drinks_list_item.view.*
//
//class TodayDrinksRecyclerAdapter(private val context: Context, private val todayDrinks: MutableList<Drinks>,
//                                 private var onItemClickListener : OnItemClickListener) :
//    RecyclerView.Adapter<TodayDrinksRecyclerAdapter.Holder>() {
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
//        val view = LayoutInflater.from(context).inflate(R.layout.drinks_list_item, p0, false)
//        return Holder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return todayDrinks.count()
//    }
//
//    override fun onBindViewHolder(p0: Holder, p1: Int) {
//        p0.bindDrinks(todayDrinks[p1])
//    }
//
//    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//        fun bindDrinks(drinks: Drinks) {
//            val resourceId = context.resources.getIdentifier(drinks.image, "drawable", context.packageName)
//            itemView.drinks_list_item_image.setImageResource(resourceId)
//            itemView.drinks_list_item_volume.text = drinks.volume.toString()
////            itemView.drinkListUnit.text = drinks.unit
//
//            itemView.drinks_list_item_delete_button.setOnClickListener {
////                val position = adapterPosition
////                DrinksToday.removeDrink(position)
////                dailyTotal -= drinks.volume
////                DrinksToday.sharedPreferences!!.edit().putInt("dailyTotal", dailyTotal).apply()
////
////                notifyDataSetChanged()
////                onItemClickListener.onItemClick(dailyTotal)
//                Toast.makeText(context, "No functionality right now", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(dailyTotal: Int)
//    }
//}
