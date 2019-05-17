package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ishiki.mizuwodrinkwater.R

class CustomGlass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_glass)
    }

    fun setCustomGlass(@Suppress("UNUSED_PARAMETER") view: View) {
        val setCustomGlassIntent = Intent(this, SetGlassActivity::class.java)
        startActivity(setCustomGlassIntent)
    }
}
