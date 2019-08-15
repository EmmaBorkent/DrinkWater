package com.ishiki.mizuwodrinkwater.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.media.Image
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.GlassesAdapter
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler

class PopupEditGlassFragment : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.popup_edit_glass, null))
                .setPositiveButton(R.string.popup_save_button,
                    DialogInterface.OnClickListener { dialog, which ->
                        // save the changes
                        TODO("Add save functionality")
                    })
                .setNegativeButton(R.string.popup_cancel_button,
                    DialogInterface.OnClickListener { dialog, which ->
                        // dialog is closed, nothing happens
                    })
                .setNeutralButton("Delete",
                    DialogInterface.OnClickListener { dialog, which ->
                        // delete the glass from the list (not the database)
                        TODO("Add delete functionality")
                    })
            //set other dialog properties
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}