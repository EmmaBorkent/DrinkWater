package com.ishiki.mizuwodrinkwater.fragments

import android.annotation.SuppressLint
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.MainActivity
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinksDatabaseHandler
import com.ishiki.mizuwodrinkwater.services.HOME_TODAY
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

//    private lateinit var layoutManager: RecyclerView.LayoutManager
//    private lateinit var adapter: DrinksDialogRecyclerAdapter
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

        val showHumanDate = Drinks().showHumanDate(date.timeInMillis)
        val todayDateText = context?.getString(R.string.home_today_date, HOME_TODAY,
            showHumanDate)
        fragment_home_today_date.text = todayDateText
//        fragment_home_volume_text.text = dailyTotal().toString()
        fragment_home_goal_text.text = Drinks.goal.toString()
        val percentage = dailyTotal() * 100 / Drinks.goal
        fragment_home_goal_percentage.text = percentage.toString()

        dailyProgressBar()
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

    private fun dailyProgressBar() {

        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)

        val bitmap = Bitmap.createBitmap(displayMetrics.widthPixels,
            displayMetrics.heightPixels, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val shapeDrawable = ShapeDrawable(RectShape())

        // Rectangle positions
        val left = 0
        val top = displayMetrics.heightPixels*0.15
        val right = displayMetrics.widthPixels*0.03
        val bottom = displayMetrics.heightPixels*0.98


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
