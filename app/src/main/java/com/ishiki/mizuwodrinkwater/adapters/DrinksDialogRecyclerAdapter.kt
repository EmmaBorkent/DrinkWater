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

class DrinksDialogRecyclerAdapter(private val glassesList: ArrayList<Glasses>,
                                  private val context: Context,
                                  private val itemClick: (Glasses) -> Unit) :
    RecyclerView.Adapter<DrinksDialogRecyclerAdapter.DrinksHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_drinks_dialog,
            parent, false)
        return DrinksHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return glassesList.size
    }

    override fun onBindViewHolder(holder: DrinksHolder, position: Int) {
        holder.bindViews(glassesList[position])
    }

    inner class DrinksHolder(itemView: View, private val itemClick: (Glasses) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.add_drink_list_image)
        private val volume = itemView.findViewById<TextView>(R.id.add_drink_list_volume)

        fun bindViews(glass: Glasses) {
            val resourceId = context.resources.getIdentifier(glass.image, "drawable",
                context.packageName)
            image.setImageResource(resourceId)
            volume.text = glass.volume.toString()
            itemView.setOnClickListener { itemClick(glass) }
        }
    }
}

//class DrinksDialogRecyclerAdapter(private val glassesList: ArrayList<Glasses>,
//                                  private val context: Context,
//                                  private val listener: ClickListenerDrinkDialog) :
//    RecyclerView.Adapter<DrinksDialogRecyclerAdapter.DrinksHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.list_item_drinks_dialog, parent, false)
//        return DrinksHolder(view, listener)
//    }
//
//    override fun getItemCount(): Int {
//        return glassesList.size
//    }
//
//    override fun onBindViewHolder(holder: DrinksHolder, position: Int) {
//        holder.bindViews(glassesList[position])
//    }
//
//    inner class DrinksHolder(itemView: View, private val listener: ClickListenerDrinkDialog) :
//        RecyclerView.ViewHolder(itemView) {
//
//        private val image = itemView.findViewById<ImageView>(R.id.add_drink_list_image)
//        private val volume = itemView.findViewById<TextView>(R.id.add_drink_list_volume)
//
//        fun bindViews(glass: Glasses) {
//            val resourceId = context.resources.getIdentifier(glass.image, "drawable", context.packageName)
//            image.setImageResource(resourceId)
//            volume.text = glass.volume.toString()
//            listener.onItemClickedAddDrink(glass, adapterPosition)
//        }
//    }
//
//}