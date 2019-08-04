//package com.ishiki.mizuwodrinkwater.adapters
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import com.ishiki.mizuwodrinkwater.R
//import com.ishiki.mizuwodrinkwater.model.Drinks
//
//class GlassesAdapter(private val context: Context, private val drinks: MutableList<Drinks>, private val itemClick: (Drinks) -> Unit) :
// androidx.recyclerview.widget.RecyclerView.Adapter<GlassesAdapter.GlassHolder>() {
//
//    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): GlassHolder {
//        val view = LayoutInflater.from(context).inflate(R.layout.glasses_list, p0, false)
//        return GlassHolder(view, itemClick)
//    }
//
//    override fun getItemCount(): Int {
//        return drinks.count()
//    }
//
//    override fun onBindViewHolder(p0: GlassHolder, p1: Int) {
//        p0.bindGlass(drinks[p1], context)
//    }
//
//    inner class GlassHolder(itemView: View, val itemClick: (Drinks) -> Unit) :
//        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
//
//        private val drinkImage = itemView.findViewById<ImageView>(R.id.glasses_list_image)
//        private val drinkVolume = itemView.findViewById<TextView>(R.id.glasses_list_volume)
//        private val drinkUnit = itemView.findViewById<TextView>(R.id.glasses_list_unit)
//
//        fun bindGlass(drink: Drinks, context: Context) {
//            val resourceId = context.resources.getIdentifier(drink.image, "drawable", context.packageName)
//            drinkImage.setImageResource(resourceId)
//            drinkVolume.text = drink.volume
//            drinkUnit.text = drink.unit
//            itemView.setOnClickListener { itemClick(drink) }
//        }
//    }
//}