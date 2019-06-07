package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.GlassesAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinkTypes.currentGlass
import com.ishiki.mizuwodrinkwater.services.DrinkTypes.drinks
import com.ishiki.mizuwodrinkwater.services.DrinkTypes.serializeCustomDrinksList
import com.ishiki.mizuwodrinkwater.services.DrinksToday
import com.ishiki.mizuwodrinkwater.services.ObjectSerializer
import kotlinx.android.synthetic.main.activity_set_glass.*

class SetGlassActivity : AppCompatActivity() {

    private lateinit var adapter: GlassesAdapter

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_glass)

        drinks.clear()

//        currentGlass = intent.getParcelableExtra(EXTRA_CURRENT)

        val image = ObjectSerializer.deserialize(
            DrinksToday.sharedPreferences?.getString("imageCustom",
                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
        val volume = ObjectSerializer.deserialize(
            DrinksToday.sharedPreferences?.getString("volumeCustom",
                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
        val unit = ObjectSerializer.deserialize(
            DrinksToday.sharedPreferences?.getString("unitCustom",
                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>

        if (image.size > 0 && volume.size > 0 && unit.size > 0) {
            if (image.size == volume.size && image.size == unit.size) {

                for ((i) in image.withIndex()) {
                    drinks.add(Drinks(image[i], volume[i], unit[i]))
                }
            }
        }

        adapter = GlassesAdapter(this, drinks) { drink ->
            // Here goes the code that you want to happen when you click on it
            println("Selected ${drink.image}")

            currentGlass = drink
            DrinksToday.sharedPreferences!!.edit().putString("currentGlassImage", currentGlass.image).apply()
            DrinksToday.sharedPreferences!!.edit().putString("currentGlassVolume", currentGlass.volume).apply()
            DrinksToday.sharedPreferences!!.edit().putString("currentGlassUnit", currentGlass.unit).apply()

            val setGlassIntent = Intent(this, MainActivity::class.java)
//            setGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
//            setGlassIntent.putExtra(EXTRA_SET, currentGlass)
            startActivity(setGlassIntent)
        }

        var spanCount = 2
        val orientation = resources.configuration.orientation
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3
        }

        val layoutManager = GridLayoutManager(this, spanCount)
        setGlassListView?.layoutManager = layoutManager
        setGlassListView?.setHasFixedSize(true)
        setGlassListView?.adapter = adapter
    }

    fun createCustomClass(@Suppress("UNUSED_PARAMETER") view: View) {
        // Print to check
        println("Into Intent on SetGlassActivity is ${currentGlass.image}")
        val customGlassIntent = Intent(this, CustomGlassActivity::class.java)
//        customGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
//        customGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
        startActivity(customGlassIntent)
    }
}
