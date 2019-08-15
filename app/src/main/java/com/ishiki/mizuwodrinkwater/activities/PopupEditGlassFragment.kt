package com.ishiki.mizuwodrinkwater.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ishiki.mizuwodrinkwater.R

class PopupEditGlassFragment : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
                        val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.popup_edit_glass, null))
                .setPositiveButton(R.string.popup_save_button,
                    DialogInterface.OnClickListener { dialog, id ->
                        // save the changes
                        TODO("Add save functionality")
                    })
                .setNegativeButton(R.string.popup_cancel_button,
                    DialogInterface.OnClickListener { dialog, id ->
                        // cancel action
                        TODO("Add cancel functionality, or is this automatic behaviour?")
                    })
            //set other dialog properties
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}