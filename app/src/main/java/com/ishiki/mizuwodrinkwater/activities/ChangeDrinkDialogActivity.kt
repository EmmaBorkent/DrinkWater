package com.ishiki.mizuwodrinkwater.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import kotlinx.android.synthetic.main.activity_change_drink_dialog.*
import kotlinx.android.synthetic.main.activity_change_drink_dialog.change_drink_volume_input

class ChangeDrinkDialogActivity : AppCompatActivity() {

    private lateinit var dbHandler: DrinksDatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_change_drink_dialog)

        // Set dialog width, because with recyclerView match_parent does not work
        val dialog = findViewById<View>(R.id.change_drink_dialog_container)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels*0.9
        dialog.layoutParams.width = width.toInt()

        // Get data from Intent
        val drinkID = intent.getLongExtra("DRINK_ID", 0)
        Log.d("CHANGE_DIALOG", "the item id is $drinkID")
        // Don't use these intents, instead use the ID to read from the database
//        val drinkImage = intent.getStringExtra("DRINK_IMAGE")
//        Log.d("intent image", "the image is $drinkImage")
//        val drinkVolume: Int? = intent.getIntExtra("DRINK_VOLUME", 250)
//        Log.d("intent volume", "The volume is $drinkVolume")

        // Read the drink from database
        dbHandler = DrinksDatabaseHandler(applicationContext)
        val drink = dbHandler.readDrink(drinkID)
        Log.d("CHANGE_DIALOG", "Drink ID ${drink.id} with volume ${drink.volume}")

        // Get drink info from database
        val resourceId = resources.getIdentifier(drink.image, "drawable", packageName)
        change_drink_image.setImageResource(resourceId)
        change_drink_volume_input.setText(drink.volume.toString())

        change_drink_save_button.setOnClickListener {
            val volume = change_drink_volume_input.text.toString().toInt()
            drink.volume = volume
            drink.id = drinkID
            Log.d("CHANGE_DIALOG", "Drink ID ${drink.id} with volume ${drink.volume}")
            dbHandler.updateDrink(drink)
            val homeFragmentIntent = Intent(applicationContext, MainActivity::class.java)
            homeFragmentIntent.putExtra("loadFragment", R.id.today)
            startActivity(homeFragmentIntent)
        }
    }
}
