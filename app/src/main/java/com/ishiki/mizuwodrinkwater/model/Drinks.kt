package com.ishiki.mizuwodrinkwater.model

import java.text.DateFormat
import java.util.*

class Drinks {

    var id: Int = 0
    lateinit var image: String
    var volume: Int = 0
    var time: Long = 0

//    constructor(id: Int, image: String, volume: Int, time: Long, goal: Int) {
//        this.id = id
//        this.image = image
//        this.time = time
//        this.goal = goal
//    }
//
//    constructor(image: String, volume: Int, goal: Int) {
//        this.image = image
//        this.volume = volume
//        this.goal = goal
//    }

    fun showHumanDate(time: Long): String {
        val dateFormat: DateFormat = DateFormat.getDateInstance()
        return dateFormat.format(Date(time).time)
    }

}