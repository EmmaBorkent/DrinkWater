package com.ishiki.mizuwodrinkwater.fragments

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.MainActivity
import com.ishiki.mizuwodrinkwater.adapters.DrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import com.ishiki.mizuwodrinkwater.services.HOME_TODAY
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        DrinksToday.sharedPreferences = this.getSharedPreferences("com.ishiki.mizuwodrinkwater", 0)
//        DrinksToday.goal = DrinksToday.sharedPreferences!!.getInt(DAILY_GOAL, DrinksToday.goal)
//        DrinksToday.dailyTotal = DrinksToday.sharedPreferences!!.getInt("dailyTotal", DrinksToday.dailyTotal)
//        DrinkTypes.currentGlass = Drinks(
//            DrinksToday.sharedPreferences?.getString("currentGlassImage", DrinkTypes.currentGlass.image).toString(),
//            DrinksToday.sharedPreferences?.getString("currentGlassVolume", DrinkTypes.currentGlass.volume).toString(),
//            DrinksToday.sharedPreferences?.getString("currentGlassUnit", DrinkTypes.currentGlass.unit).toString()
//        )
//
//        DrinksToday.history_drinks_list.clear()
//
//        val image = ObjectSerializer.deserialize(
//            DrinksToday.sharedPreferences?.getString("image",
//                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//        val volume = ObjectSerializer.deserialize(
//            DrinksToday.sharedPreferences?.getString("volume",
//                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//        val unit = ObjectSerializer.deserialize(
//            DrinksToday.sharedPreferences?.getString("unit",
//                ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//
//        if (image.size > 0 && volume.size > 0 && unit.size > 0) {
//            if (image.size == volume.size && image.size == unit.size) {
//
//                for ((i) in image.withIndex()) {
//                    DrinksToday.history_drinks_list.add(Drinks(image[i], volume[i], unit[i]))
//                }
//            }
//        }

//        val resourceId = resources.getIdentifier(DrinkTypes.currentGlass.image, "drawable")
//        mainDrinkImage?.setBackgroundResource(resourceId)
//        mainWaterAmount?.text = DrinkTypes.currentGlass.volume
//        mainTextDailyTotal?.text = DrinksToday.dailyTotal.toString()
//        mainTextGoalNumber?.text = DrinksToday.goal.toString()

//        adapter = TodayDrinksRecyclerAdapter(MainActivity(), DrinksToday.history_drinks_list, object :
//            TodayDrinksRecyclerAdapter.OnItemClickListenerGlassesAdapter {
//            override fun onItemClick(dailyTotal: Int) {
//                mainTextDailyTotal.text = dailyTotal.toString()
//            }
//        })
//        history_drinks_list?.adapter = adapter
//        val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
//        history_drinks_list?.layoutManager = layoutManager
//        history_drinks_list?.setHasFixedSize(true)

//        mainDrinkImage.setOnClickListener {
//            val setGlassIntent = Intent(context, SetGlassActivity::class.java)
//            setGlassIntent.putExtra(EXTRA_DAILY, DrinksToday.dailyTotal)
////          setGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
//            startActivity(setGlassIntent)
//        }

//        todayAddButton.setOnClickListener {
//            Toast.makeText(context?.applicationContext, "Clicked FAB on Today Fragment", Toast.LENGTH_LONG).show()
//        }

//        mainButtonAdd?.setOnClickListener {
//            DrinksToday.addDrink()
//            adapter.notifyDataSetChanged()
//            mainTextDailyTotal.text = DrinksToday.dailyTotal.toString()
//            if (DrinksToday.dailyTotal >= DrinksToday.goal) {
//                goalReached()
//            }
//        }

//        mainButtonSetGoal.setOnClickListener {
//            val setGoalIntent = Intent(context, GoalActivity::class.java)
//            startActivity(setGoalIntent)
//        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Set subtitle, percentage and goal values
        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
        val todayDateText = context?.getString(R.string.home_today_date, HOME_TODAY,
            showHumanDate)
        fragment_home_today_date.text = todayDateText
//        fragment_home_volume_text.text = dailyTotal().toString()
        fragment_home_goal_text_button.text = Drinks.goal.toString()
        val percentage = dailyTotal() * 100 / Drinks.goal
        fragment_home_goal_percentage.text = percentage.toString()

        fragment_home_notifications.setOnClickListener {
            Toast.makeText(context,"Notificaties", Toast.LENGTH_SHORT).show()
        }

        fragment_home_change_main_display.setOnClickListener {
            Toast.makeText(context, "Verander Display Weergave", Toast.LENGTH_SHORT).show()
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

        bottomSheetBehavior.bottomSheetCallback = object: BottomSheetBehavior.BottomSheetCallback() {
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
        }
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
