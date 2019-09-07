package com.ishiki.mizuwodrinkwater.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.DrinksDialogRecyclerAdapter
import com.ishiki.mizuwodrinkwater.fragments.GlassesFragment
import com.ishiki.mizuwodrinkwater.model.Glasses
import kotlinx.android.synthetic.main.activity_drink_dialog.*
import kotlinx.android.synthetic.main.fragment_home.*

class DrinksDialogActivity : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: DrinksDialogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_drink_dialog)

        // Set dialog width, because with recyclerView match_parent does not work
        val dialog = findViewById<View>(R.id.activity_drink_dialog_container)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels*0.9
        dialog.layoutParams.width = width.toInt()

//        val dialogWidth = windowManager.defaultDisplay.getMetrics(DisplayMetrics())
//        val width = displ
//        dialog.layoutParams.width = windowManager.defaultDisplay
//
//        dialog.display.width

//        val layoutParams = view.layoutParams
//        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
//        view.layoutParams = layoutParams



        val glassesList2 = arrayListOf(
            Glasses("water01", 250),
            Glasses("water02", 500),
            Glasses("water03", 150)
        )

        activity_drink_dialog_edit_button.setOnClickListener {
            Log.d("DrinksDialogActivity", "Clicked Edit Button")
            val glassesFragmentIntent = Intent(applicationContext, MainActivity::class.java)
            glassesFragmentIntent.putExtra("loadFragment", R.id.glasses)
            startActivity(glassesFragmentIntent)
        }

        // This works in HomeFragment...
        layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        activity_drink_dialog_recyclerview.layoutManager = layoutManager
        adapter = DrinksDialogRecyclerAdapter(glassesList2, this) { glass ->
            Log.d("DrinksDialogActivity", "Item ${glass.image} clicked")
        }
        activity_drink_dialog_recyclerview.adapter = adapter

//        layoutManager = LinearLayoutManager(applicationContext, HORIZONTAL, false)
//        activity_drink_dialog_recyclerview.layoutManager = layoutManager
//
//        for (item in Glasses.glassesList) {
//            Log.d("DrinksDialogActivity", item.image)
//        }
//
//        adapter = DrinksDialogRecyclerAdapter(Glasses.glassesList, applicationContext) { glass ->
//            Log.d("DrinksDialogActivity", "Clicked ${glass.image}")
//        }
//        activity_drink_dialog_recyclerview.adapter = adapter
//
//        activity_drink_dialog_edit_button.setOnClickListener {
//            Log.d("DrinksDialogActivity", "Clicked Edit Button")
//        }
    }
}
