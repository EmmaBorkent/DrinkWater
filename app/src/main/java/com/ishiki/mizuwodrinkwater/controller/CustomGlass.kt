package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DataService.drinks
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CURRENT
import kotlinx.android.synthetic.main.activity_custom_glass.*

class CustomGlass : AppCompatActivity() {

    var name = drinks[0].image
    private var number = 1
    private lateinit var currentGlass: Drinks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_glass)

        currentGlass = intent.getParcelableExtra(EXTRA_CURRENT)
        // To check if the intent works
        println("Intent in CustomGlassActivity ${currentGlass.image}")
    }

    fun nextImage(@Suppress("UNUSED_PARAMETER") view: View) {
        if (number < 10) {
            number += 1
        } else {
            number = 1
        }

        name = "water0$number"
        val resourceId = resources.getIdentifier(name, "drawable", packageName)
        customGlassImage.setImageResource(resourceId)

        // Print to check
        println("The drink is $name")
    }

    fun prevImage(@Suppress("UNUSED_PARAMETER") view: View) {
        if (number > 1) {
            number -= 1
        } else {
            number = 10
        }

        name = "water0$number"

        val resourceId = resources.getIdentifier(name, "drawable", packageName)
        customGlassImage.setImageResource(resourceId)

        // Print to check
        println("The drink is $name")
    }

    fun setCustomGlass(@Suppress("UNUSED_PARAMETER") view: View) {

        currentGlass = Drinks(name, "250", "ml")
        drinks.add(0, currentGlass)

        // Print to check
        println("Added a custom drink ${currentGlass.image}")
        println("List now contains ${drinks.size} items")

        val setCustomGlassIntent = Intent(this, SetGlassActivity::class.java)
        setCustomGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
        startActivity(setCustomGlassIntent)
    }
}
