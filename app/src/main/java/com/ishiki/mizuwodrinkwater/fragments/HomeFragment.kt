package com.ishiki.mizuwodrinkwater.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.ChangeDrinkDialogActivity
import com.ishiki.mizuwodrinkwater.adapters.DrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: DrinksRecyclerAdapter
    private lateinit var dbHandler: DrinksDatabaseHandler
    private val date: Calendar = Calendar.getInstance()
    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>
    private lateinit var state: String
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Set subtitle, percentage and goal values
        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
        val todayDateText = context?.getString(R.string.home_today_date, HOME_TODAY,
            showHumanDate)
        fragment_home_today_date.text = todayDateText

        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_file), 0
        )

        if (sharedPreferences != null) {
            val goal = sharedPreferences!!.getInt("goal", Drinks.totalGoal)
            fragment_home_goal_text_button.text = goal.toString()
            state = sharedPreferences!!.getString(PREFS_STATE, UNIT_PERCENTAGE)!!
            Log.d("STATE", "SharedPrefs state is $state")
        } else {
            fragment_home_goal_text_button.text = Drinks.totalGoal.toString()
            state = UNIT_PERCENTAGE
            Log.d("STATE", "No SharedPrefs, state is $state")
        }

        // Find state and set percentage or volume according to setting
        when (state) {
            UNIT_PERCENTAGE -> setPercentage()
            UNIT_VOLUME -> setVolume()
        }

        fragment_home_notifications.setOnClickListener {
            val remindersFragment = RemindersFragment()
            val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, remindersFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        fragment_home_change_main_display.setOnClickListener {
            switchPercentageVolume()
        }

        fragment_home_goal_text_button.setOnClickListener {
            // Don't use intent, it is slow.
//            val goalFragmentIntent = Intent(context, MainActivity::class.java)
//            goalFragmentIntent.putExtra("loadFragment", R.id.goal)
//            startActivity(goalFragmentIntent)

            val goalFragment = GoalFragment()
            val fragmentTransaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, goalFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        // Create Bottom Sheet
        bottomSheetBehavior = BottomSheetBehavior.from<ConstraintLayout>(activity_bottom_sheet)
        bottomSheet()

        // Show drinks today in Bottom Sheet
        showItems()

    }

    private fun dailyTotal(): Int {
        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)

        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)+1
        val day = date.get(Calendar.DATE)
        val parseFrom: Date? = format.parse("$year-$month-$day 00:00:00")
        val parseTo: Date? = format.parse("$year-$month-$day 23:59:00")

        val databaseDrinks: ArrayList<Drinks>
        databaseDrinks = dbHandler.findDay(parseTo!!.time, parseFrom!!.time)

        var totalVolume = 0
        for (i in databaseDrinks.iterator()) {
            totalVolume += i.volume
        }
        return totalVolume
    }

    private fun bottomSheet() {

        // setOnClickListeners for arrow and edit button
//        home_fragment_display_activity.setOnClickListener(this)
//        home_fragment_edit_activity.setOnClickListener(this)

        home_fragment_display_activity.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        home_fragment_edit_activity.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        // Get display height to calculate peekHeight for the Bottom Sheet
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        // This might go wrong with screens with different pixel densities
        val halfScreenHeight = displayMetrics.heightPixels*0.41
        bottomSheetBehavior.peekHeight = halfScreenHeight.toInt()

        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}

            override fun onStateChanged(bottomSheet: View, newState: Int) {

                val animationClockwise = AnimationUtils.loadAnimation(activity,
                    R.anim.rotate_clockwise)
                animationClockwise.fillAfter = true
                val animationCounterClockwise = AnimationUtils.loadAnimation(activity,
                    R.anim.rotate_counter_clockwise)
                animationCounterClockwise.fillAfter = true

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    home_fragment_display_activity.startAnimation(animationClockwise)
                    home_fragment_edit_activity.visibility = View.INVISIBLE

                    home_fragment_display_activity.setOnClickListener {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }

                    adapter.notifyDataSetChanged()


                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    home_fragment_display_activity.startAnimation(animationCounterClockwise)
                    home_fragment_edit_activity.visibility = View.VISIBLE
                    when (state) {
                        UNIT_PERCENTAGE -> setPercentage()
                        UNIT_VOLUME -> setVolume()
                    }

                    home_fragment_display_activity.setOnClickListener {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }

                    adapter.notifyDataSetChanged()
                }

                // For state logging
//                when (newState) {
//                    BottomSheetBehavior.STATE_EXPANDED -> Log.i("STATE", "Expanded State")
//                    BottomSheetBehavior.STATE_COLLAPSED -> Log.i("STATE", "Collapsed State")
//                    BottomSheetBehavior.STATE_DRAGGING -> Log.i("STATE", "Dragging...")
//                    BottomSheetBehavior.STATE_SETTLING -> Log.i("STATE", "Settling...")
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> Log.i("STATE", "Half Expended State")
//                    BottomSheetBehavior.STATE_HIDDEN -> Log.i("STATE", "Hidden State")
//                }
            }
        })

    }

    private fun setPercentage() {
        val percentage = dailyTotal() * 100 / Drinks.totalGoal
        fragment_home_goal_main_view.text = percentage.toString()
        val unit = context?.getString(R.string.unit_percentage)
        fragment_home_percentage_unit.text = "$unit"
        fragment_home_change_main_display.text = resources.getString(R.string.unit_ml)
        state = UNIT_PERCENTAGE
        sharedPreferences!!.edit().putString(PREFS_STATE, state).apply()
        Log.d("STATE", "The state is $state")
    }

    private fun setVolume() {
        val volume: Int = dailyTotal()
        fragment_home_goal_main_view.text = volume.toString()
        val unit = context?.getString(R.string.unit_ml)
        fragment_home_percentage_unit.text = "$unit"
        fragment_home_change_main_display.text = resources.getString(R.string.unit_percentage)
        state = UNIT_VOLUME
        sharedPreferences!!.edit().putString(PREFS_STATE, state).apply()
        Log.d("STATE", "The state is $state")
    }

    private fun switchPercentageVolume() {
        if (state == UNIT_PERCENTAGE) {
            setVolume()
            Log.d("STATE", "Changed state to $state")

        } else if (state == UNIT_VOLUME) {
            setPercentage()
            Log.d("STATE", "Changed state to $state")
        }

        // when state is percentage or volume change text of unit!
    }

    private fun showItems() {
        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)
        val drinksList: ArrayList<Drinks> = ArrayList()

        layoutManager = LinearLayoutManager(context!!.applicationContext)
        drinks_recyclerview_small.layoutManager = layoutManager

        adapter = DrinksRecyclerAdapter(drinksList, context!!.applicationContext,
            bottomSheetBehavior) { item, position ->
            Log.d("adapter", "the item ${item.image} has position $position")
            val changeDrinkIntent = Intent(context, ChangeDrinkDialogActivity::class.java)
            // Use ID to read from data base in the other activity
            changeDrinkIntent.putExtra("DRINK_ID", item.id)
            startActivity(changeDrinkIntent)
        }
        drinks_recyclerview_small.adapter = adapter

        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)+1
        val day = date.get(Calendar.DATE)
        val parseFrom: Date? = format.parse("$year-$month-$day 00:00:00")
        val parseTo: Date? = format.parse("$year-$month-$day 23:59:00")

        val databaseDrinks: ArrayList<Drinks>
        databaseDrinks = dbHandler.findDay(parseTo!!.time, parseFrom!!.time)

        for (i in databaseDrinks.iterator()) {
            val drink = Drinks()
            drink.id = i.id
            drink.image = i.image
            drink.volume = i.volume
            drink.time = i.time
            drinksList.add(drink)
        }
        drinksList.reverse()

        adapter.notifyDataSetChanged()
    }

}
