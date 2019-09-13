package com.ishiki.mizuwodrinkwater.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler

//private val onItemClickListener: AdapterView.OnItemClickListenerGlassesAdapter
class DrinksRecyclerAdapter(private val drinksList: ArrayList<Drinks>,
                            private val context: Context,
                            private val itemClick: (Drinks, Int) -> Unit) :
        RecyclerView.Adapter<DrinksRecyclerAdapter.DrinksHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_drinks_fragment, parent,
                false)
        return DrinksHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }

    override fun onBindViewHolder(holder: DrinksHolder, position: Int) {
        holder.bindDrinks(drinksList[position])
    }

    inner class DrinksHolder(itemView: View, val itemClick: (Drinks, Int) -> Unit) :
            RecyclerView.ViewHolder(itemView) {

        private val drinkImage = itemView.findViewById(R.id.drinks_list_item_image)
                as ImageView
        private val drinkVolume = itemView.findViewById(R.id.drinks_list_item_volume)
                as TextView
        private val drinkTime = itemView.findViewById(R.id.drinks_list_item_time)
                as TextView
        private val drinkEdit = itemView.findViewById(R.id.drinks_list_item_edit_button)
                as Button
        private val drinkDelete = itemView.findViewById(R.id.drinks_list_item_delete_button)
                as Button

        fun bindDrinks(drink: Drinks) {
            val resourceId = context.resources.getIdentifier(drink.image, "drawable",
                    context.packageName)
            drinkImage.setImageResource(resourceId)
            drinkVolume.text = drink.volume.toString()
            drinkTime.text = drink.showHumanTime(drink.time)

            drinkEdit.setOnClickListener { itemClick(drink, adapterPosition) }
            drinkDelete.setOnClickListener {
                val db = DrinksDatabaseHandler(context)
                db.deleteDrink(drink.id)
                drinksList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
                Log.d("adapter", "delete item")
            }
        }

//        override fun onClick(v: View?) {
//
//            val mPosition: Int = adapterPosition
//            val drink = drinksList[mPosition]
//
//            when(v?.id) {
//                drinkDelete.id -> {
//                    deleteDrink(drink.id)
//                    drinksList.removeAt(adapterPosition)
//                    notifyItemRemoved(adapterPosition)
//                }
//                drinkEdit.id -> {
//                    editDrink(drink)
//                }
//            }
//        }

//        private fun editDrink(drinks: Drinks) {
//            Toast.makeText(context, "Clicked Edit Button", Toast.LENGTH_SHORT).show()
//        }

    }

//    interface OnItemClickListenerGlassesAdapter {
//        fun onItemClick(adapter: DrinksRecyclerAdapter)
//    }

}