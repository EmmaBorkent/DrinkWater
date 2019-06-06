package com.ishiki.mizuwodrinkwater.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.services.DrinksToday.goal
import kotlinx.android.synthetic.main.activity_goal.*

class GoalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)
    }

    fun calculateGoal(@Suppress("UNUSED_PARAMETER") view: View) {
        val weight = goalWeightInput.text.toString()

        if (weight.isNotEmpty()) {
            goal = weight.toInt() * 33
            goalInput.setText(goal.toString())
        } else {
            val toast = Toast.makeText(this, "Please enter your weight to calculate your daily goal",
                Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    fun setGoal(@Suppress("UNUSED_PARAMETER") view: View) {
        val setGoalIntent = Intent(this, MainActivity::class.java)

        if (goalInput.text.isNotEmpty()) {
            goal = goalInput.text.toString().toInt()
            startActivity(setGoalIntent)
        } else {
            val toast = Toast.makeText(this, "Please enter or calculate your daily goal", Toast.LENGTH_SHORT)
            toast.show()
        }
    }
}
