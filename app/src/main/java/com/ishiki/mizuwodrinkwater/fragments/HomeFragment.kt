package com.ishiki.mizuwodrinkwater.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.adapters.DrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import com.ishiki.mizuwodrinkwater.services.HOME_TODAY
import com.ishiki.mizuwodrinkwater.services.UNIT_PERCENTAGE
import com.ishiki.mizuwodrinkwater.services.UNIT_VOLUME
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
    private var state = "percent"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    //    fun Group.setAllOnClickListener(listener: View.OnClickListener?) {
//        referencedIds.forEach { id ->
//            rootView.findViewById<View>(id).setOnClickListener(listener)
//        }
//    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Set subtitle, percentage and goal values
        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
        val todayDateText = context?.getString(R.string.home_today_date, HOME_TODAY,
            showHumanDate)
        fragment_home_today_date.text = todayDateText

//        fragment_home_volume_text.text = dailyTotal().toString()
        fragment_home_goal_text_button.text = Drinks.goal.toString()

        setPercentage()
//        val percentage = dailyTotal() * 100 / Drinks.goal
//        fragment_home_goal_main_view.text = percentage.toString()

//        fragment_home_goal_main_view.setOnClickListener {
////
//        }


//        val group = R.id.fragment_home_main_click_view
//
//        fragment_home_main_click_view.post {
//            group.setAllOnClickListener(object : View.OnClickListener {
//                override fun onClick(v: View) {
//                    val text = (v as Button).text
//                    Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
//                }
//            })
//        }

//        fragment_home_main_click_view.setAllOnClickListener(View.OnClickListener {
//            Toast.makeText(context,"What am I clicking?!", Toast.LENGTH_SHORT).show()
//        })

//        fragment_home_main_click_view.setOnClickListener {
//            switchPercentageVolume()
//            Toast.makeText(context,"Test Bigger Click View", Toast.LENGTH_SHORT).show()
//        }

        fragment_home_notifications.setOnClickListener {
            Toast.makeText(context,"Notificaties", Toast.LENGTH_SHORT).show()
        }

        fragment_home_change_main_display.setOnClickListener {
            //            Toast.makeText(context, "Verander Display Weergave", Toast.LENGTH_SHORT).show()
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
        // This might go wrong with screens with different pixel densities!
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
        val percentage = dailyTotal() * 100 / Drinks.goal
        fragment_home_goal_main_view.text = percentage.toString()
        val unit = context?.getString(R.string.main_unit_percentage)
        fragment_home_percentage_unit.text = "$unit"
        state = UNIT_PERCENTAGE
        Log.d("STATE", "The state is $state")
    }

    private fun setVolume() {
        val volume: Int = dailyTotal()
        fragment_home_goal_main_view.text = volume.toString()
        val unit = context?.getString(R.string.main_unit_ml)
        fragment_home_percentage_unit.text = "$unit"
        state = UNIT_VOLUME
        Log.d("STATE", "The state is $state")
    }

    private fun switchPercentageVolume() {
        if (state == UNIT_PERCENTAGE) {
            setVolume()
            fragment_home_change_main_display.text = resources.getString(R.string.main_unit_percentage)
            Log.d("STATE", "The state is $state")

        } else if (state == UNIT_VOLUME) {
            setPercentage()
            fragment_home_change_main_display.text = resources.getString(R.string.main_unit_ml)
            Log.d("STATE", "The state is $state")
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
