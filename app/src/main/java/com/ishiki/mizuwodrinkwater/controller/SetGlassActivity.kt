package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_AMOUNT
import kotlinx.android.synthetic.main.activity_set_glass.*

class SetGlassActivity : AppCompatActivity() {

    var waterAmount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_glass)
        waterAmount = intent.getIntExtra(EXTRA_AMOUNT, 400)
        println(waterAmount)
    }

    fun setGlass250(view: View) {
        println("Set Glass to 250 ml")
    }

    fun setGlass500(view: View) {
        waterAmount = 500
        println("Set Glass to 500 ml")

        val glass500Intent = Intent(this, MainActivity::class.java)
        glass500Intent.putExtra(EXTRA_AMOUNT, waterAmount)
        startActivity(glass500Intent)
    }
}
