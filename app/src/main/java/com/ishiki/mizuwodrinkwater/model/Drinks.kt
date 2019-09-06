package com.ishiki.mizuwodrinkwater.model

import java.text.DateFormat
import java.util.*

class Drinks(var image: String, var volume: Int) {

    var id: Int = 0
//    lateinit var image: String
//    var volume: Int = 0
    var time: Long = 0
    var goal: Int = 2000

    fun showHumanDate(time: Long): String {
        val dateFormat: DateFormat = DateFormat.getDateInstance()
        return dateFormat.format(Date(time).time)
    }

}