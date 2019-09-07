package com.ishiki.mizuwodrinkwater.unused
//
//import android.annotation.SuppressLint
//import android.app.AlertDialog
//import android.app.Dialog
//import android.os.Bundle
//import androidx.fragment.app.DialogFragment
//import com.ishiki.mizuwodrinkwater.R
//
//class PopupEditGlassFragment : DialogFragment() {
//
//    @SuppressLint("InflateParams")
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            val builder = AlertDialog.Builder(it)
//            val inflater = requireActivity().layoutInflater
//            builder.setView(inflater.inflate(R.layout.dialog_glasses, null))
//            //  Set other dialog properties here
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//
//}