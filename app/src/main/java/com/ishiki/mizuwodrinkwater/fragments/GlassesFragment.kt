package com.ishiki.mizuwodrinkwater.fragments

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
import com.ishiki.mizuwodrinkwater.activities.GlassesDialogActivity
import com.ishiki.mizuwodrinkwater.adapters.GlassesRecyclerAdapter
import com.ishiki.mizuwodrinkwater.model.Glasses
import com.ishiki.mizuwodrinkwater.services.ObjectSerializer
import com.ishiki.mizuwodrinkwater.services.EXTRA_CHECK
import com.ishiki.mizuwodrinkwater.services.EXTRA_GLASS
import com.ishiki.mizuwodrinkwater.services.EXTRA_POSITION
import com.ishiki.mizuwodrinkwater.services.EXTRA_VOLUME
import kotlinx.android.synthetic.main.fragment_glasses.*

class GlassesFragment : Fragment() {

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: GlassesRecyclerAdapter
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glasses, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // You need to open the sharedPreferences first, before you can read from them, duh
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_file), 0
        )

        if (sharedPreferences != null) {

            Glasses.glassesList.clear()

            @Suppress("UNCHECKED_CAST")
            val image = ObjectSerializer.deserialize(
                sharedPreferences!!
                    .getString("image", ObjectSerializer.serialize(ArrayList<String>()))
            )
                    as ArrayList<String>

            @Suppress("UNCHECKED_CAST")
            val volume = ObjectSerializer.deserialize(
                sharedPreferences!!
                    .getString("volume", ObjectSerializer.serialize(ArrayList<String>()))
            )
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
            val glassesPopupIntent = Intent(context, GlassesDialogActivity::class.java)
            glassesPopupIntent.putExtra(EXTRA_GLASS, glass.image)
            glassesPopupIntent.putExtra(EXTRA_VOLUME, glass.volume)
            glassesPopupIntent.putExtra(EXTRA_CHECK, "adapter")
            glassesPopupIntent.putExtra(EXTRA_POSITION, position)
            startActivity(glassesPopupIntent)
        }
        glasses_recycler_view.adapter = adapter
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

        // Open sharedPreferences, then write to it by creating a SharedPreferences Editor
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_file), 0
        ) ?: return
        with(sharedPreferences!!.edit()) {
            putString("image", ObjectSerializer.serialize(image))
            putString("volume", ObjectSerializer.serialize(volume))
            apply()
        }
    }
}

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
