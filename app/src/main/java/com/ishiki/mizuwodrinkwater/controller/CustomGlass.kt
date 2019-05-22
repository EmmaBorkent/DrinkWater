package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CURRENT
import kotlinx.android.synthetic.main.activity_custom_glass.*

class CustomGlass : AppCompatActivity() {

    lateinit var name: String

    private lateinit var currentGlass: Drinks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_glass)

        currentGlass = intent.getParcelableExtra(EXTRA_CURRENT)
    }

    private var number = 1
    fun nextImage(@Suppress("UNUSED_PARAMETER") view: View) {
        if (number < 10) {
            number += 1
        } else {
            number = 1
        }

        val name = "water0$number"

        val resourceId = resources.getIdentifier(name, "drawable", packageName)
        customGlassImage.setImageResource(resourceId)

        // Print to check
        println(number)
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
        println(number)
    }

    fun setCustomGlass(@Suppress("UNUSED_PARAMETER") view: View) {

        println("I add a custom drink $name")
//        drinks.add(Drinks(name,"250 ml", "ml"))

        val setCustomGlassIntent = Intent(this, SetGlassActivity::class.java)
        setCustomGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
        startActivity(setCustomGlassIntent)
    }
}
