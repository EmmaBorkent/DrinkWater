package com.ishiki.mizuwodrinkwater.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.DrinksDialogRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.model.Glasses
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import com.ishiki.mizuwodrinkwater.services.ObjectSerializer
import kotlinx.android.synthetic.main.activity_drink_dialog.*

class DrinksDialogActivity : AppCompatActivity() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: DrinksDialogRecyclerAdapter
    private var sharedPreferences: SharedPreferences? = null
    private lateinit var dbHandler: DrinksDatabaseHandler

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



//        val glassesList2 = arrayListOf(
//            Glasses("water01", 250),
//            Glasses("water02", 500),
//            Glasses("water03", 150)
//        )

        activity_drink_dialog_edit_button.setOnClickListener {
            Log.d("DrinksDialogActivity", "Clicked Edit Button")
            val glassesFragmentIntent = Intent(applicationContext, MainActivity::class.java)
            glassesFragmentIntent.putExtra("loadFragment", R.id.glasses)
            startActivity(glassesFragmentIntent)
        }

        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_file), 0
        )

        if (sharedPreferences != null) {

            Glasses.glassesList.clear()

            @Suppress("UNCHECKED_CAST")
            val image = ObjectSerializer.deserialize(
                sharedPreferences!!
                    .getString("image", ObjectSerializer.serialize(ArrayList<String>()))
            )
                    as ArrayList<String>

            @Suppress("UNCHECKED_CAST")
            val volume = ObjectSerializer.deserialize(
                sharedPreferences!!
                    .getString("volume", ObjectSerializer.serialize(ArrayList<String>()))
            )
                    as ArrayList<String>

            if (image.size > 0 && volume.size > 0) {
                if (image.size == volume.size) {
                    for ((i) in image.withIndex()) {
                        Glasses.glassesList.add(Glasses(image[i], volume[i].toInt()))
                    }
                }
            }

            val imageTest: String? = sharedPreferences!!.getString("imageTest", "water01")
            Log.d("sharedPreferences", "imageTest: $imageTest")
        }

        dbHandler = DrinksDatabaseHandler(this)
        layoutManager = LinearLayoutManager(this, HORIZONTAL, false)
        activity_drink_dialog_recyclerview.layoutManager = layoutManager
        adapter = DrinksDialogRecyclerAdapter(Glasses.glassesList, this) { glass ->
            Log.d("DrinksDialogActivity", "Item ${glass.image} clicked")
            val drink = Drinks()
            drink.image = glass.image
            drink.volume = glass.volume
            dbHandler.createDrink(drink)
            val homeFragmentIntent = Intent(applicationContext, MainActivity::class.java)
            homeFragmentIntent.putExtra("loadFragment", R.id.today)
            startActivity(homeFragmentIntent)
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
