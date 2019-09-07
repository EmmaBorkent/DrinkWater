package com.ishiki.mizuwodrinkwater.unused
//
//import android.annotation.SuppressLint
//import android.app.AlertDialog
//import android.app.Dialog
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.widget.ImageButton
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.fragment.app.DialogFragment
//import com.ishiki.mizuwodrinkwater.R
//import com.ishiki.mizuwodrinkwater.model.Glasses
//import com.ishiki.mizuwodrinkwater.model.Glasses.Companion.imageNameToResourceId
//import com.ishiki.mizuwodrinkwater.unused.DataSetChanged
//
//class GlassesDialog(val glass: Glasses, val position: Int,
//                    private val dataSetChangeListener: DataSetChanged) : DialogFragment() {
//
////    private lateinit var listener: NoticeDialogListener
////
////    interface NoticeDialogListener {
//////        fun onDialogPositiveClick(dialog: DialogFragment)
//////        fun onDialogNegativeClick(dialog: DialogFragment)
////    }
////
////    override fun onAttach(context: Context?) {
////        super.onAttach(context)
////        // Verify that the host activity implements the callback interface
////        try {
////            // Instantiate the NoticeDialogListener so we can send events to the host
////            listener = context as NoticeDialogListener
////        } catch (e: ClassCastException) {
////            // The activity doesn't implement the interface, throw exception
////            throw ClassCastException((context.toString() + " must implement NoticeDialogListener"))
////        }
////    }
//
////    @SuppressLint("InflateParams")
////    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
////        return activity?.let {
////            val builder = AlertDialog.Builder(it)
////            val inflater = requireActivity().layoutInflater
////            builder.setView(inflater.inflate(R.layout.dialog_glasses, null))
////            // Set other dialog properties here
////            builder.create()
////        } ?: throw IllegalStateException("Activity cannot be null")
////    }
//
//    @SuppressLint("InflateParams")
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return activity?.let {
//            val layoutInflater = LayoutInflater.from(it)
//            val view = layoutInflater.inflate(R.layout.dialog_glasses, null)
//            val builder = AlertDialog.Builder(it)
////            builder.create()
//
//            val imageView: ImageView = view.findViewById(R.id.popup_image) as ImageView
//            val arrowRight: ImageButton = view.findViewById(R.id.popup_arrow_right) as ImageButton
//            val arrowLeft: ImageButton = view.findViewById(R.id.popup_arrow_left) as ImageButton
//            val volumeInput: TextView = view.findViewById(R.id.popup_volume_input) as TextView
//            val deleteButton: ImageButton = view.findViewById(R.id.popup_delete_button) as ImageButton
//            val saveButton: ImageButton = view.findViewById(R.id.popup_save_button) as ImageButton
//
//            var number = glass.image[6].toString().toInt()
//
//            val imageResource = context?.resources!!.getIdentifier(glass.image, "drawable",
//                context!!.packageName)
//            imageView.setImageResource(imageResource)
//
//            arrowRight.setOnClickListener {
//                if (number < 10) {
//                    number += 1
//                } else {
//                    number = 1
//                }
//                imageView.setImageResource(imageNameToResourceId(number, context!!))
//            }
//
//            arrowLeft.setOnClickListener {
//                if (number > 1) {
//                    number -= 1
//                } else {
//                    number = 10
//                }
//                imageView.setImageResource(imageNameToResourceId(number, context!!))
//            }
//
//            volumeInput.text = glass.volume.toString()
//
//            deleteButton.setOnClickListener {
//                Glasses.deleteGlass(position)
//                dismiss()
//                dataSetChangeListener.onDataSetChanged()
//            }
//
//            saveButton.setOnClickListener {
//                val newVolume = volumeInput.text.toString().toInt()
//                val newImage = "water$number"
//                Glasses.updateGlass(newImage, newVolume, position)
//                dismiss()
//                dataSetChangeListener.onDataSetChanged()
//            }
//            builder.setView(view)
//            builder.create()
//        } ?: throw IllegalStateException("Activity cannot be null")
//    }
//
////    val layoutInflater = LayoutInflater.from(this)
////    val promptView = layoutInflater.inflate(R.layout.prompt, null)
//
////    val alertD = AlertDialog.Builder(this).create()
//
////    val userInput = promptView.findViewById(R.id.userInput) as EditText
////    val btnAdd1 = promptView.findViewById(R.id.btnAdd1) as Button
////    val btnAdd2 = promptView.findViewById(R.id.btnAdd2) as Button
////    btnAdd1.setOnClickListener(object:OnClickListener() {
////        fun onClick(v:View) {
////            // btnAdd1 has been clicked
////        }
////    })
////    btnAdd2.setOnClickListener(object:OnClickListener() {
////        fun onClick(v:View) {
////            // btnAdd2 has been clicked
////        }
////    })
////    alertD.setView(promptView)
////    alertD.show()
//
////    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
////        return activity?.let {
////            // Build the dialog and set up the button click handlers
////            val builder = AlertDialog.Builder(it)
////
////            builder.setMessage(R.string.dialog_fire_missiles)
////                .setPositiveButton(R.string.fire,
////                    DialogInterface.OnClickListener { dialog, id ->
////                        // Send the positive button event back to the host activity
////                        listener.onDialogPositiveClick(this)
////                    })
////                .setNegativeButton(R.string.cancel,
////                    DialogInterface.OnClickListener { dialog, id ->
////                        // Send the negative button event back to the host activity
////                        listener.onDialogNegativeClick(this)
////                    })
////
////            builder.create()
////        } ?: throw IllegalStateException("Activity cannot be null")
////    }
//
////        override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
////            return activity?.let {
////                // Use the Builder class for convenient dialog construction
////                val builder = AlertDialog.Builder(it)
////                builder.setMessage(R.string.dialog_fire_missiles)
////                    .setPositiveButton(R.string.fire,
////                        DialogInterface.OnClickListener { dialog, id ->
////                            // FIRE ZE MISSILES!
////                        })
////                    .setNegativeButton(R.string.cancel,
////                        DialogInterface.OnClickListener { dialog, id ->
////                            // User cancelled the dialog
////                        })
////                // Create the AlertDialog object and return it
////                builder.create()
////            } ?: throw IllegalStateException("Activity cannot be null")
////        }
////    }
//
//}