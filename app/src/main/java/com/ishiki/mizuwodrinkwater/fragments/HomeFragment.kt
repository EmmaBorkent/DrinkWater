package com.ishiki.mizuwodrinkwater.fragments

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.MainActivity
import com.ishiki.mizuwodrinkwater.adapters.DrinksRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import com.ishiki.mizuwodrinkwater.services.HOME_TODAY
import kotlinx.android.synthetic.main.fragment_drinks.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.list_item_drinks_fragment.*
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
    private var minusOne = -1

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

        showItems()

        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
        val todayDateText = context?.getString(R.string.home_today_date, HOME_TODAY,
            showHumanDate)
        fragment_home_today_date.text = todayDateText
//        fragment_home_volume_text.text = dailyTotal().toString()
        fragment_home_goal_text.text = Drinks.goal.toString()
        val percentage = dailyTotal() * 100 / Drinks.goal
        fragment_home_goal_percentage.text = percentage.toString()

        fragment_home_notifications.setOnClickListener {
            Toast.makeText(context,"Notificaties", Toast.LENGTH_SHORT).show()
        }

        fragment_home_change_main_display.setOnClickListener {
            Toast.makeText(context, "Verander Display Weergave", Toast.LENGTH_SHORT).show()
        }

        home_fragment_edit_activity.setOnClickListener {
            Toast.makeText(context, "Activiteit Aanpassen", Toast.LENGTH_SHORT).show()
        }


        val mBottomSheetBehavior = BottomSheetBehavior
            .from(activity_bottom_sheet)
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val halfScreenHeight = displayMetrics.heightPixels*0.38
        mBottomSheetBehavior.peekHeight = halfScreenHeight.toInt()

        home_fragment_display_activity.setOnClickListener {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        mBottomSheetBehavior.bottomSheetCallback = object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    home_fragment_display_activity.setOnClickListener {
                        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                    }
                } else if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                    home_fragment_display_activity.setOnClickListener {
                        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

//                when (newState) {
//                    BottomSheetBehavior.STATE_EXPANDED -> expanded()
//                    BottomSheetBehavior.STATE_COLLAPSED -> collapsed()
//                    BottomSheetBehavior.STATE_DRAGGING -> Log.i("STATE", "Dragging...")
//                    BottomSheetBehavior.STATE_SETTLING -> Log.i("STATE", "Setteling...")
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> Log.i("STATE", "Half Expended State")
//                    BottomSheetBehavior.STATE_HIDDEN -> Log.i("STATE", "Hidden State")
//                }
            }

            fun expanded() {
                Log.i("STATE", "Expanded State")
                home_fragment_display_activity.setOnClickListener {
                    mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }

            fun collapsed() {
                Log.i("STATE", "Collapsed State")
                home_fragment_display_activity.setOnClickListener {
                    mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            }


        }

//        mBottomSheetBehavior.setBottomSheetCallback(object:BottomSheetBehavior.BottomSheetCallback() {
//            fun onStateChanged(@NonNull bottomSheet:View, newState:Int) {
//                when (newState) {
//                    BottomSheetBehavior.STATE_COLLAPSED -> mTextViewState.setText("Collapsed")
//                    BottomSheetBehavior.STATE_DRAGGING -> mTextViewState.setText("Dragging...")
//                    BottomSheetBehavior.STATE_EXPANDED -> mTextViewState.setText("Expanded")
//                    BottomSheetBehavior.STATE_HIDDEN -> mTextViewState.setText("Hidden")
//                    BottomSheetBehavior.STATE_SETTLING -> mTextViewState.setText("Settling...")
//                }
//            }
//        })



    }

    private fun dailyTotal(): Int {
        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)

        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)+1
        val day = date.get(Calendar.DATE)
        val parseFrom = format.parse("$year-$month-$day 00:00:00")
        val parseTo = format.parse("$year-$month-$day 23:59:00")

        val databaseDrinks: ArrayList<Drinks>
        databaseDrinks = dbHandler.findDay(parseTo.time, parseFrom.time)

        var totalVolume = 0
        for (i in databaseDrinks.iterator()) {
            totalVolume += i.volume
        }
        return totalVolume
    }

    private fun showItems() {
        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)
        val drinksList: ArrayList<Drinks> = ArrayList()

        layoutManager = LinearLayoutManager(context!!.applicationContext)
        drinks_recyclerview_small.layoutManager = layoutManager

        adapter = DrinksRecyclerAdapter(drinksList, context!!.applicationContext) { item,
                                                                                    position ->
            Log.d("adapter", "the item ${item.image} has position $position")
        }
        drinks_recyclerview_small.adapter = adapter

        val year = date.get(Calendar.YEAR)
        val month = date.get(Calendar.MONTH)+1
        val day = date.get(Calendar.DATE)
        val parseFrom = format.parse("$year-$month-$day 00:00:00")
        val parseTo = format.parse("$year-$month-$day 23:59:00")

        val databaseDrinks: ArrayList<Drinks>
        databaseDrinks = dbHandler.findDay(parseTo.time, parseFrom.time)

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

//    private fun roundedCorners(bitmap: Bitmap): Bitmap {
////        val output: Bitmap = Bitmap.createBitmap(bitmap.width, bitmap.height,
////            Bitmap.Config.ARGB_8888)
////        val canvas = Canvas(output)
//
////        val color = resources.getColor(R.color.text)
////        val paint = Paint()
////        val rect = Rect(0,0,bitmap.width,bitmap.height)
////        val rectF = RectF()
////        val roundPx = 12F
////
////        paint.isAntiAlias = true
////        canvas.drawARGB(0,0,0,0)
//////        paint.color = color
////        canvas.drawRoundRect(rectF, roundPx,roundPx,paint)
////
////        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
////        canvas.drawBitmap(bitmap, rect, rect, paint)
////
////        return output
//    }

//    fun createAddDrinkDialog() {
//
//        val dialog = Dialog(context!!)
//        dialog.setContentView(R.layout.x_popup_add_drink)
//        val popupAddDrink = dialog.findViewById(R.id.popup_add_drink_recycler_view) as RecyclerView
//        val popupEditGlassesButton = dialog.findViewById(R.id.popup_add_drink_edit_glasses_button) as ImageButton
//
//        layoutManager = LinearLayoutManager(dialog.context)
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
//        popupAddDrink.layoutManager = layoutManager
//        adapter = DrinksDialogRecyclerAdapter(Glasses.glassesList, context!!, this)
//        popupAddDrink.adapter = adapter
//
//        dialog.show()
//        val lp = WindowManager.LayoutParams()
//        lp.copyFrom(dialog.window?.attributes)
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT
//        dialog.window?.attributes = lp
//
//        popupEditGlassesButton.setOnClickListener {
//            Toast.makeText(context, "Clicked Add on Home Fragment", Toast.LENGTH_SHORT).show()
//
//            dialog.dismiss()
//            (activity as MainActivity).replaceFragment(GlassesFragment())
//        }
//    }

//    private fun createPopup() {
//        val view = layoutInflater.inflate(R.layout.popup_edit_glass, null)
////        val drinkImage = view.popupGlassImage
////        val drinkVolume = view.popupVolume
//
//        dialogBuilder = AlertDialog.Builder(activity!!.applicationContext).setView(view)
//        dialog = dialogBuilder.create()
//        dialog.show()
//
//        popupSelectButton.setOnClickListener {
//
//        }
//    }

//    private fun goalReached() {
//        val toast = Toast.makeText(context, "Congratulations! You reached your daily goal!",
//            Toast.LENGTH_LONG)
//        toast.show()
//    }

}
