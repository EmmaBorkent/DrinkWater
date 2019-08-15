package com.ishiki.mizuwodrinkwater.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.services.DrinkTypes
import kotlinx.android.synthetic.main.activity_custom_glass.*

class CreateGlassFragment : Fragment() {

    var name = "water01"
    private var number = 1
    private lateinit var newGlass: Drinks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_glass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_glass_arrow_right.setOnClickListener {
            if (number < 10) {
                number += 1
            } else {
                number = 1
            }

            name = "water0$number"
            val resourceId = resources.getIdentifier(name, "drawable", context!!.packageName)
            customGlassImage.setImageResource(resourceId)

            // Print to check
            println("The drink is $name")
        }

        create_glass_arrow_left.setOnClickListener {
            if (number > 1) {
                number -= 1
            } else {
                number = 10
            }

            name = "water0$number"
            val resourceId = resources.getIdentifier(name, "drawable", context!!.packageName)
            customGlassImage.setImageResource(resourceId)

            // Print to check
            println("The drink is $name")
        }

        create_glass_button.setOnClickListener {

            val checkEmpty = create_glass_volume_input.text
            val volume = create_glass_volume_input.text.toString().toInt()

            if (checkEmpty.isNotEmpty()) {
                newGlass = Drinks(name, volume)
                DrinkTypes.glasses.add(0, newGlass)
                (activity as MainActivity).replaceFragment(GlassesFragment())
            } else {
                Toast.makeText(context!!.applicationContext,
                    "Please enter a volume for the glass", Toast.LENGTH_SHORT).show()
            }

        }

    }

//    fun setCustomGlass(@Suppress("UNUSED_PARAMETER") view: View) {
//
//        val volume = customVolumeInput.text.toString()
//
//        if (volume.isNotEmpty()) {
//            newDrink = Drinks(name, volume, "ml")
//            drinks.add(0, newDrink)
//            DrinkTypes.serializeCustomDrinksList()
//
//            // Print to check
//            println("Added a custom drink ${newDrink.image}, with volume ${newDrink.volume}")
//            println("List now contains ${drinks.size} items")
//
//            val setCustomGlassIntent = Intent(this, SetGlassActivity::class.java)
////            setCustomGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
////            setCustomGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
//            startActivity(setCustomGlassIntent)
//        } else {
//            val toast = Toast.makeText(this, "Please enter a volume for the glass", Toast.LENGTH_SHORT)
//            toast.show()
//        }
//    }

}




