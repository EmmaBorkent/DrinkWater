package com.ishiki.mizuwodrinkwater.activities

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ishiki.mizuwodrinkwater.R

class PopupEditGlassFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
                        val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            builder.setView(inflater.inflate(R.layout.popup_edit_glass, null))
                .setPositiveButton(R.string.popup_save_button,
                    DialogInterface.OnClickListener { dialog, id ->
                        // save the changes
                    })
                .setNegativeButton(R.string.popup_cancel_button,
                    DialogInterface.OnClickListener { dialog, id ->
                        // cancel action
                    })
            //set other dialog properties
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}

//inner class PopupEditGlassFragment : DialogFragment() {
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            val builder = AlertDialog.Builder(it)
//            val inflater = requireActivity().layoutInflater
////                    val view: View = LayoutInflater.from(context as? MainActivity).inflate(R.layout.popup_edit_glass, null)
//            builder.setView(inflater.inflate(R.layout.popup_edit_glass, null))
//                .setPositiveButton(R.string.popup_save_button,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // save the changes
//                    })
//                .setNegativeButton(R.string.popup_cancel_button,
//                    DialogInterface.OnClickListener { dialog, id ->
//                        // cancel action
//                    })
//            //set other dialog properties
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//}