package com.ishiki.mizuwodrinkwater.activities


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.ishiki.mizuwodrinkwater.R
import com.ishiki.mizuwodrinkwater.services.DrinkTypes
import kotlinx.android.synthetic.main.fragment_glasses.*

class GlassesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_glasses, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setGlassButton.setOnClickListener {
//            // Print to check
//            println("Into Intent on SetGlassActivity is ${DrinkTypes.currentGlass.image}")
//            val customGlassIntent = Intent(context, CustomGlassActivity::class.java)
////        customGlassIntent.putExtra(EXTRA_DAILY, dailyTotal)
////        customGlassIntent.putExtra(EXTRA_CURRENT, currentGlass)
//            startActivity(customGlassIntent)
//        }
    }


}
