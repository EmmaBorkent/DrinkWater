package com.ishiki.mizuwodrinkwater.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.activities.GlassesPopupActivity
import com.ishiki.mizuwodrinkwater.activities.MainActivity
import com.ishiki.mizuwodrinkwater.adapters.GlassesRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Glasses
import com.ishiki.mizuwodrinkwater.services.ObjectSerializer
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_CHECK
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_GLASS
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_POSITION
import com.ishiki.mizuwodrinkwater.utilities.EXTRA_VOLUME
import kotlinx.android.synthetic.main.fragment_glasses.*

class GlassesFragment : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var adapter: GlassesRecyclerAdapter
    var sharedPreferences: SharedPreferences? = null

//    private var sharedPref: SharedPreferences? = null
//    val PREFS_FILENAME = "com.ishiki.mizuwodrinkwater.prefs"
//    val IMAGE = "image"
//    val VOLUME = "VOLUME"

    // Create a SharedPreference file
//    var sharedPreferences = activity?.getSharedPreferences(
//        getString(R.string.shared_preferences_file), 0)

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d("lifecycle", "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("lifecycle", "onCreateView")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glasses, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("lifecycle", "onActivityCreated")

//        sharedPref = activity?.getSharedPreferences(PREFS_FILENAME, 0)
//        val image = ObjectSerializer.deserialize(sharedPref!!.getString(IMAGE,
//            ObjectSerializer.serialize(ArrayList<String>())))
//        val volume = ObjectSerializer.deserialize(sharedPref!!
//            .getString("volume", ObjectSerializer.serialize(ArrayList<String>())))
//
//        if (image.size > 0 && volume.size > 0) {
//            if (image.size == volume.size) {
//                for ((i) in image.withIndex()) {
//                    Glasses.glassesList.add(Glasses(image[i], volume[i].toInt()))
//                }
//            }
//        }

//        Log.d("test", "before if statement")

//

        // You need to open the sharedPreferences first, before you can read from them, duh
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_file), 0)
        if (sharedPreferences != null) {

            Glasses.glassesList.clear()

            val image = ObjectSerializer.deserialize(sharedPreferences!!
                .getString("image", ObjectSerializer.serialize(ArrayList<String>())))
                    as ArrayList<String>
            val volume = ObjectSerializer.deserialize(sharedPreferences!!
                .getString("volume", ObjectSerializer.serialize(ArrayList<String>())))
                    as ArrayList<String>

            if (image.size > 0 && volume.size > 0) {
                if (image.size == volume.size) {
                    for ((i) in image.withIndex()) {
                        Glasses.glassesList.add(Glasses(image[i], volume[i].toInt()))
                    }
                }
            }

            val imageTest: String? = sharedPreferences!!.getString("imageTest", "water01")
            Log.d("sharedPreferences", "imageTest: $imageTest")
        }

        layoutManager = GridLayoutManager(context!!.applicationContext, 2)
        glasses_recycler_view.layoutManager = layoutManager
        adapter = GlassesRecyclerAdapter(Glasses.glassesList, context!!) { glass, position ->
//            Toast.makeText(context, "Clicked ${glass.image}", Toast.LENGTH_SHORT).show()
            val glassesPopupIntent = Intent(context, GlassesPopupActivity::class.java)
            glassesPopupIntent.putExtra(EXTRA_GLASS, glass.image)
            glassesPopupIntent.putExtra(EXTRA_VOLUME, glass.volume)
            glassesPopupIntent.putExtra(EXTRA_CHECK, "adapter")
            glassesPopupIntent.putExtra(EXTRA_POSITION, position)
            startActivity(glassesPopupIntent)
        }
        glasses_recycler_view.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        Log.d("lifecycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("lifecycle", "onResume")
        adapter.notifyDataSetChanged()
        serializeGlassesList()
    }

    private fun serializeGlassesList() {

        val image: ArrayList<String> = ArrayList()
        val volume: ArrayList<String> = ArrayList()

        for (glass: Glasses in Glasses.glassesList) {
            image.add(glass.image)
            volume.add(glass.volume.toString())
        }

        // This block of code is working
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_file), 0) ?: return
        // Create a SharedPreferences Editor
        with(sharedPreferences!!.edit()) {
            putString("image", ObjectSerializer.serialize(image))
            putString("volume", ObjectSerializer.serialize(volume))

            putString("imageTest", "water03")
            commit()
            // or apply??
        }
        // Check
        val imageTest: String? = sharedPreferences!!.getString("imageTest", "water01")
        Log.d("sharedPreferences", "imageTest: $imageTest")

//        var sharedPreferences = activity?.getSharedPreferences(getString(R.string.shared_preferences_file),
//            Context.MODE_PRIVATE) ?: return with (sharedPreferences!!.edit()) {
//            putString("image", ObjectSerializer.serialize(image))
//            putString("volume", ObjectSerializer.serialize(volume))
//            apply()
//        }
    }

    override fun onPause() {
        super.onPause()
        Log.d("lifecycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lifecycle", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("lifecycle", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lifecycle", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("lifecycle", "onDetach")
    }
}

//        if (DrinksToday.sharedPreferences!!.contains("imageCustom")) {
//            drinks.clear()
//
//            val image = ObjectSerializer.deserialize(
//                DrinksToday.sharedPreferences?.getString("imageCustom",
//                    ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//            val volume = ObjectSerializer.deserialize(
//                DrinksToday.sharedPreferences?.getString("volumeCustom",
//                    ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//            val unit = ObjectSerializer.deserialize(
//                DrinksToday.sharedPreferences?.getString("unitCustom",
//                    ObjectSerializer.serialize(ArrayList<String>()))) as ArrayList<String>
//
//            if (image.size > 0 && volume.size > 0 && unit.size > 0) {
//                if (image.size == volume.size && image.size == unit.size) {
//
//                    for ((i) in image.withIndex()) {
//                        drinks.add(Drinks(image[i], volume[i], unit[i]))
//                    }
//                }
//            }
//        }

//    private fun showItems() {
//
//        dbHandler = DrinksDatabaseHandler(context!!.applicationContext)
//
//        glassesList = ArrayList()
//        glassesList.reverse()
//        glassListItem = ArrayList()
//
//        layoutManager = GridLayoutManager(context!!.applicationContext, 2)
//        glasses_recycler_view.layoutManager = layoutManager
//        adapter = GlassesAdapterOLD2(glassListItem, context!!.applicationContext)
//        glasses_recycler_view.adapter = adapter
//
//        glassesList = dbHandler.readAllDrinks()
//
//        for (i in glassesList.iterator()) {
//            val drink = Drinks(i.image, i.volume)
////            drink.image =
////            drink.volume =
//
//            glassListItem.add(drink)
//        }
//
//        adapter.notifyDataSetChanged()
//
//    }
//
//    setGlassButton.setOnClickListener {
//        // Print to check
//        println("Into Intent on SetGlassActivity is ${DrinkTypes.currentGlass.image}")
//        val customGlassIntent = Intent(context, CustomGlassActivity::class.java)
////        customGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
////        customGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
//        startActivity(customGlassIntent)
//    }
