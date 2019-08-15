package com.ishiki.mizuwodrinkwater.adapters

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.MainActivity
import com.ishiki.mizuwodrinkwater.activities.PopupEditGlassFragment
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.CreateDialog
import com.ishiki.mizuwodrinkwater.services.OnItemClickListener

class GlassesAdapter(private val glassesList: ArrayList<Drinks>, private val context: Context,
                     private val listener: OnItemClickListener) :
    RecyclerView.Adapter<GlassesAdapter.GlassHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlassHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.glasses_list_item, parent, false)
        return GlassHolder(view, /*context, glassesList,*/ listener)
    }

    override fun getItemCount(): Int {
        return glassesList.size
    }

    override fun onBindViewHolder(holder: GlassHolder, position: Int) {
        holder.bindViews(glassesList[position])
    }

    inner class GlassHolder(
        itemView: View/*,
        context: Context,
        list: ArrayList<Drinks>*/,
//        private val mContext = context
//        private val mList = list
        private val listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {

//        lateinit var dialog: Dialog
//        private val glassesListItem: ConstraintLayout = itemView.findViewById(R.id.glasses_list_item) as ConstraintLayout

        private val image = itemView.findViewById(R.id.glasses_list_image) as ImageView
        private val volume = itemView.findViewById(R.id.glasses_list_volume) as TextView
        private val edit = itemView.findViewById(R.id.glasses_list_edit_button) as Button

        fun bindViews(drinks: Drinks) {
//            dialog.setContentView(R.layout.popup_edit_glass)

//            (context as MainActivity).editGlass()

            val builder = Dialog(context)
            builder.setContentView(R.layout.popup_edit_glass)

            val popupImage: ImageView = builder.findViewById(R.id.popup_image) as ImageView
            val popupArrowLeft: ImageButton = builder.findViewById(R.id.popup_arrow_left) as ImageButton
            val popArrowRight: ImageButton = builder.findViewById(R.id.popup_arrow_right) as ImageButton
            val popVolumeInput: TextView = builder.findViewById(R.id.popup_volume_input) as TextView

            val popupImageResource = context.resources.getIdentifier(drinks.image, "drawable",
                context.packageName)
            popupImage.setImageResource(popupImageResource)
            popupArrowLeft.setImageResource(R.drawable.ic_keyboard_arrow_left)
            popArrowRight.setImageResource(R.drawable.ic_keyboard_arrow_right)
//            popupArrowLeft.setOnClickListener {  }
//            popArrowRight.setOnClickListener {  }
            popVolumeInput.text = drinks.volume.toString()

            val resourceId = context.resources.getIdentifier(drinks.image, "drawable", context.packageName)
            image.setImageResource(resourceId)
            volume.text = drinks.volume.toString()
            edit.setOnClickListener {

//                (context as MainActivity).supportFragmentManager

//                Toast.makeText(context, "Clicked Edit Button", Toast.LENGTH_SHORT).show()
                builder.show()
//                listener.onItemClicked(itemView, adapterPosition)
            }
        }

//        fun editGlass() {
//            val popupFragment = PopupEditGlassFragment()
//            popupFragment.show(supportFragmentManager, "edit glass")
//        }

        fun onClick(view: View, position: Int) {
            listener.onItemClicked(view, position)
        }

        @SuppressLint("InflateParams")
        private fun editGlass(glass: Drinks) {

//            val newFragment = PopupEditGlassFragment()
//            newFragment.show()

//            val image = view.popup_image
//            val leftArrow = view.popup_arrow_left
//            val rightArrow = view.popup_arrow_right
//            val volume = view.popup_volume_input
//            val saveButton = view.popup_save_button

//            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(it)
//            val view: View = LayoutInflater.from(context as? MainActivity).inflate(R.layout.popup_edit_glass, null)
//            dialogBuilder.setView(view)
//            dialogBuilder.create()
////            Toast.makeText(this@MainActivity, "Clicked Edit Button", Toast.LENGTH_SHORT).show()
//
//            dialogBuilder.show()


//            val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context).setView(view)
//            lateinit var dialogBuilder: AlertDialog.Builder
//            dialogBuilder = AlertDialog.Builder(context).setView(view)
//            val content: View = LayoutInflater.from(context).inflate(R.layout.popup_edit_glass, null)

//            val dialog = dialogBuilder.create()
//            dialog.show()

//            lateinit var dialog: AlertDialog
//            dialog = dialogBuilder.create()
//            dialog.show()

//            saveButton.setOnClickListener {
//                Toast.makeText(context, "Clicked Save Button", Toast.LENGTH_SHORT).show()
//                dialog.dismiss()
////                val volumeCheck = volume.text.toString()
////
////                if (!TextUtils.isEmpty(volumeCheck)) {
////                    glass.volume = volumeCheck.toInt()
////
////                }
//            }
        }

    }

}