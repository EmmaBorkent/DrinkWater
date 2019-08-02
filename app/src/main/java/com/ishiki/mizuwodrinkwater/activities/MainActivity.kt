package com.ishiki.mizuwodrinkwater.activities

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.ishiki.mizuwodrinkwater.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        outState?.run {
//            putInt(EXTRA_DAILY, dailyTotal)
//        }
//    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.today -> {
                replaceFragment(TodayFragment())
                println("Opened TODAY from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.history -> {
                replaceFragment(HistoryFragment())
                println("Opened HISTORY from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.goal -> {
                replaceFragment(GoalFragment())
                println("Opened GOAL from itemId")
                return@OnNavigationItemSelectedListener true
            }
            R.id.glasses -> {
                replaceFragment(GlassesFragment())
                println("Opened GLASSES from itemId")
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

//    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (intent.extras != null) {
            val getIntent = intent.extras?.getInt("loadFragment")
            bottomNavigation.selectedItemId = getIntent!!
        } else {
            replaceFragment(TodayFragment())
        }
    }

    private fun replaceFragment(fragment: androidx.fragment.app.Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}