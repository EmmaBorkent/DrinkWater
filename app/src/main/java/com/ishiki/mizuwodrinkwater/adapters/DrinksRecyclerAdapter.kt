package com.ishiki.mizuwodrinkwater.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler

class DrinksRecyclerAdapter(private val drinksList: ArrayList<Drinks>,
                            private val context: Context) :
        RecyclerView.Adapter<DrinksRecyclerAdapter.DrinksHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.drinks_list_item, parent,
                false)
        return DrinksHolder(view, context, drinksList)
    }

    override fun getItemCount(): Int {
        return drinksList.size
    }

    override fun onBindViewHolder(holder: DrinksHolder, position: Int) {
        holder.bindDrinks(drinksList[position])
    }

    inner class DrinksHolder(itemView: View, context: Context, list: ArrayList<Drinks>) :
            RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val mContext = context
        val mList = list

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

        fun bindDrinks(drinks: Drinks) {
            val resourceId = context.resources.getIdentifier(drinks.image, "drawable",
                    context.packageName)
            drinkImage.setImageResource(resourceId)
            drinkVolume.text = drinks.volume.toString()
            drinkTime.text = drinks.showHumanDate(drinks.time)
            drinkEdit.setOnClickListener(this)
            drinkDelete.setOnClickListener(this)

//            drinkEdit.setOnClickListener {
//                Toast.makeText(context, "Clicked Edit Button", Toast.LENGTH_SHORT).show()
//            }
//
//            drinkDelete.setOnClickListener {
//                Toast.makeText(context, "Clicked Delete Button", Toast.LENGTH_SHORT).show()
//            }
        }

        override fun onClick(v: View?) {

            val mPosition: Int = adapterPosition
            val drink = mList[mPosition]

            when(v?.id) {
                drinkDelete.id -> {
                    deleteDrink(drink.id)
                    mList.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
                drinkEdit.id -> {
                    editDrink(drink)
                }
            }
        }

        private fun deleteDrink(id: Int) {
            val database = DrinksDatabaseHandler(mContext)
            database.deleteDrink(id)
        }

        private fun editDrink(drinks: Drinks) {
            Toast.makeText(context, "Clicked Edit Button", Toast.LENGTH_SHORT).show()
        }

    }

    // TodayDrinksRecyclerAdapter
    // Hieruit nodig de binding van de layout
//    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        fun bindDrinks(drinks: Drinks) {
//            val resourceId = context.resources.getIdentifier(drinks.image, "drawable", context.packageName)
//            itemView.drinks_list_item_image.setImageResource(resourceId)
//            itemView.drinks_list_item_volume.text = drinks.volume.toString()
//
//            itemView.drinks_list_item_delete_button.setOnClickListener {
//                Toast.makeText(context, "No functionality right now", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    interface OnItemClickListener {
//        fun onItemClick(dailyTotal: Int)
//    }

}