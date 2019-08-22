package com.ishiki.mizuwodrinkwater.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.ishiki.mizuwodrinkwater.R
import kotlinx.android.synthetic.main.popup_edit_glass.*

class PopupEditGlassFragment : DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.popup_edit_glass, null))
            //  Set other dialog properties here
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}