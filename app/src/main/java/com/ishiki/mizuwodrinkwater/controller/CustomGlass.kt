package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import kotlinx.android.synthetic.main.activity_custom_glass.*
import kotlin.random.Random

class CustomGlass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_glass)
    }

    fun setCustomGlass(@Suppress("UNUSED_PARAMETER") view: View) {
        val setCustomGlassIntent = Intent(this, SetGlassActivity::class.java)
        startActivity(setCustomGlassIntent)
    }

    var number = 1
    fun nextImage(view: View) {
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

    fun prevImage(view: View) {
        if (number > 1) {
            number -= 1
        } else {
            number = 10
        }

        val name = "water0$number"

        val resourceId = resources.getIdentifier(name, "drawable", packageName)
        customGlassImage.setImageResource(resourceId)

        // Print to check
        println(number)
    }
}
