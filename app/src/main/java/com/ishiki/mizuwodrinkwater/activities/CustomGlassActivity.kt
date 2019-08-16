package com.ishiki.mizuwodrinkwater.activities
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.View
//import android.widget.Toast
//import com.ishiki.mizuwodrinkwater.R
//import com.ishiki.mizuwodrinkwater.model.Drinks
//import com.ishiki.mizuwodrinkwater.services.DrinkTypes
////import com.ishiki.mizuwodrinkwater.services.DrinkTypes.drinks
//import kotlinx.android.synthetic.main.activity_custom_glass.*
//
//class CustomGlassActivity : AppCompatActivity() {
//
//    var name = "water01"
//    private var number = 1
//    private lateinit var newDrink: Drinks
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_custom_glass)
//
//        dailyTotal = intent.getIntExtra(EXTRA_DAILY, dailyTotal)
//        currentGlass = intent.getParcelableExtra(EXTRA_CURRENT)
//         To check if the intent works
//        println("Intent in CustomGlassActivity ${currentGlass.image}")
//    }
//
//    fun nextImage(@Suppress("UNUSED_PARAMETER") view: View) {
//        if (number < 10) {
//            number += 1
//        } else {
//            number = 1
//        }
//
//        name = "water0$number"
//        val resourceId = resources.getIdentifier(name, "drawable", packageName)
//        customGlassImage.setImageResource(resourceId)
//
//        // Print to check
//        println("The drink is $name")
//    }
//
//    fun prevImage(@Suppress("UNUSED_PARAMETER") view: View) {
//        if (number > 1) {
//            number -= 1
//        } else {
//            number = 10
//        }
//
//        name = "water0$number"
//        val resourceId = resources.getIdentifier(name, "drawable", packageName)
//        customGlassImage.setImageResource(resourceId)
//
//        // Print to check
//        println("The drink is $name")
//    }
//
//    fun setCustomGlass(@Suppress("UNUSED_PARAMETER") view: View) {
//
//        val volume = customVolumeInput.text.toString()
//
//        if (volume.isNotEmpty()) {
//            newDrink = Drinks(name, volume, "ml")
//            drinks.add(0, newDrink)
//            DrinkTypes.serializeCustomDrinksList()
//
//            // Print to check
//            println("Added a custom drink ${newDrink.image}, with volume ${newDrink.volume}")
//            println("List now contains ${drinks.size} items")
//
//            val setCustomGlassIntent = Intent(this, SetGlassActivity::class.java)
////            setCustomGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
////            setCustomGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
//            startActivity(setCustomGlassIntent)
//        } else {
//            val toast = Toast.makeText(this, "Please enter a volume for the glass", Toast.LENGTH_SHORT)
//            toast.show()
//        }
//    }
//}
