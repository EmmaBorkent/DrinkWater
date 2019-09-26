package com.ishiki.mizuwodrinkwater.model

import java.text.DateFormat
import java.util.*

class Drinks {

    var id: Int = 0
    lateinit var image: String
    var volume: Int = 0
    var time: Long = 0

    companion object {
        var goal: Int = 2500
    }

    fun showHumanDate(time: Long): String {
        val dateFormat: DateFormat = DateFormat.getDateInstance()
        return dateFormat.format(Date(time).time)
    }

    fun showHumanTime(time: Long): String {
        val timeFormat: DateFormat = DateFormat.getTimeInstance(DateFormat.SHORT)
        return timeFormat.format(Date(time).time)
    }

}