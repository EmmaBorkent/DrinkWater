package com.ishiki.mizuwodrinkwater.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import kotlinx.android.synthetic.main.fragment_goal.*

class GoalFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_goal, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_file), 0
        )

        if (sharedPreferences != null) {

            val weight = sharedPreferences!!.getInt("weight", Drinks.weight)
            val extra = sharedPreferences!!.getInt("extra", Drinks.extra)
            val calculatedGoal = sharedPreferences!!.getInt("calculatedGoal", Drinks.calculatedGoal)
            val goal = sharedPreferences!!.getInt("goal", Drinks.totalGoal)

            goal_weight.text = weight.toString()
            goal_extra.text = extra.toString()
            goal_calculated.text = calculatedGoal.toString()
            goal_total.text = goal.toString()

        } else {
            setValues()
        }

        goal_weight_add_button.setOnClickListener { addWeight() }
        goal_weight_remove_button.setOnClickListener { subtractWeight() }
        goal_extra_add_button.setOnClickListener { addExtra() }
        goal_extra_remove_button.setOnClickListener { subtractExtra() }

    }

    private fun setValues() {

        goal_weight.text = Drinks.weight.toString()
        goal_extra.text = Drinks.extra.toString()
        goal_calculated.text = Drinks.calculatedGoal.toString()
        goal_total.text = Drinks.totalGoal.toString()

        // Open sharedPreferences, then write to it by creating a SharedPreferences Editor
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_file), 0
        ) ?: return
        with(sharedPreferences!!.edit()) {
            putInt("weight", Drinks.weight)
            putInt("extra", Drinks.extra)
            putInt("calculatedGoal", Drinks.calculatedGoal)
            putInt("goal", Drinks.totalGoal)
            apply()
        }

        // Short version
//      sharedPreferences!!.edit().putInt(DAILY_GOAL, goal).apply()
    }

    private fun addWeight() {
        if (Drinks.weight <= 200) {
            Drinks.weight += 1
        }
        calculateGoal()
        setValues()
    }

    private fun subtractWeight() {
        if (Drinks.weight >= 0 ) {
            Drinks.weight -= 1
        }
        calculateGoal()
        setValues()
    }

    private fun addExtra() {
        if (Drinks.extra <= 950) {
            Drinks.extra += 50
        }
        calculateGoal()
        setValues()
    }

    private fun subtractExtra() {
        if (Drinks.extra >= 0) {
            Drinks.extra -= 50
        }
        calculateGoal()
        setValues()
    }

    private fun calculateGoal() {
        Drinks.calculatedGoal = Drinks.weight * 33
        Drinks.totalGoal = Drinks.calculatedGoal + Drinks.extra
    }

}
