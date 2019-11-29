package com.ishiki.mizuwodrinkwater.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ishiki.mizuwodrinkwater.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_reminders.*


class RemindersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminders, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.glassesAddButton?.hide()

        reminders_back_arrow_left.setOnClickListener {
            activity?.glassesAddButton?.show()
            val homeFragment = HomeFragment()
            val fragmentTransaction = fragmentManager!!.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainer, homeFragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        reminders_on_off.setOnClickListener {
            when (reminders_on_off.isChecked) {
                true -> {
                    reminders_when_container.visibility = View.VISIBLE
                    reminders_time_container.visibility = View.VISIBLE
                    reminders_how_often_container.visibility = View.VISIBLE
                }
                false -> {
                    reminders_when_container.visibility = View.INVISIBLE
                    reminders_time_container.visibility = View.INVISIBLE
                    reminders_how_often_container.visibility = View.INVISIBLE
                }
            }
        }

    }

}
