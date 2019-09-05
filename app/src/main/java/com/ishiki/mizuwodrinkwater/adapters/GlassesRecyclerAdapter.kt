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

class GlassesRecyclerAdapter(private val glassesList: ArrayList<Glasses>,
                             private val context: Context,
                             private val itemClick: (Glasses, Int) -> Unit) :
    RecyclerView.Adapter<GlassesRecyclerAdapter.GlassesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassesHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.glasses_list_item, parent,
            false)
        return GlassesHolder(view, itemClick)
    }

    override fun getItemCount(): Int {
        return glassesList.size
    }

    override fun onBindViewHolder(holder: GlassesHolder, position: Int) {
        holder.bindViews(glassesList[position], context)
    }

    inner class GlassesHolder(itemView: View,
                              val itemClick: (Glasses, Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById(R.id.glasses_list_image) as ImageView
        private val volume = itemView.findViewById(R.id.glasses_list_volume) as TextView

        fun bindViews(glass: Glasses, context: Context) {
            val resourceId: Int = context.resources.getIdentifier(glass.image, "drawable",
                context.packageName)
            image.setImageResource(resourceId)
            volume.text = glass.volume.toString()
            itemView.setOnClickListener { itemClick(glass, adapterPosition) }
        }
    }

}

//package com.ishiki.mizuwodrinkwater.adapters
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.ishiki.mizuwodrinkwater.R
//import com.ishiki.mizuwodrinkwater.model.Glasses
//
//class GlassesRecyclerAdapter(private val glassesList: ArrayList<Glasses>,
//                             private val context: Context,
//                             private val itemClick: (Glasses) -> Unit) :
//    RecyclerView.Adapter<GlassesRecyclerAdapter.GlassesHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassesHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.glasses_list_item, parent,
//            false)
//        return GlassesHolder(view, itemClick)
//    }
//
//    override fun getItemCount(): Int {
//        return glassesList.size
//    }
//
//    override fun onBindViewHolder(holder: GlassesHolder, position: Int) {
//        holder.bindViews(glassesList[position], context)
//    }
//
//    inner class GlassesHolder(itemView: View,
//                              val itemClick: (Glasses) -> Unit) :
//        RecyclerView.ViewHolder(itemView) {
//        private val image = itemView.findViewById(R.id.glasses_list_image) as ImageView
//        private val volume = itemView.findViewById(R.id.glasses_list_volume) as TextView
//
//        fun bindViews(glass: Glasses, context: Context) {
//            val resourceId: Int = context.resources.getIdentifier(glass.image, "drawable",
//                context.packageName)
//            image.setImageResource(resourceId)
//            volume.text = glass.volume.toString()
//            itemView.setOnClickListener { itemClick(glass) }
//        }
//    }
//
//}