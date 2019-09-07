package com.ishiki.mizuwodrinkwater.unused
//
//import android.content.Context
//import android.widget.ImageView
//import com.ishiki.mizuwodrinkwater.R
//import com.ishiki.mizuwodrinkwater.activities.MainActivity
//import java.lang.Exception
//import java.lang.IllegalArgumentException
//import java.lang.reflect.Field
//
//open class LeftAndRightArrow(val context: Context) {
//
//    lateinit var name: String
//
//    fun rightArrow(image: ImageView) {
//        var number = 1
//        if (number < 10) {
//            number += 1
//            println("The number is $number")
//        } else {
//            number = 1
//        }
//        name = "water0$number"
//        val resourceId = context.resources.getIdentifier(name, "drawable", context.packageName)
//        image.setImageResource(resourceId)
//    }
//
//    fun leftArrow(image: ImageView) {
//        var number = 1
//        if (number > 1) {
//            number -= 1
//        } else {
//            number = 10
//        }
//        name = "water0$number"
//        val resourceId = context.resources.getIdentifier(name, "drawable", context.packageName)
//        image.setImageResource(resourceId)
//    }
//
//    @Throws(IllegalArgumentException::class)
//    fun getResourceNameFromClassByID(aClass: Class<*>, resourceID: Int): String {
//        // Get all fields from the class passed
//        val drawableFields = aClass.fields
//        // Loop through all fields
//        for (i in drawableFields) {
//            try {
//                // All fields within the subclasses of R
//                // are integers, so we need to type-check here
//                // Compare to the resourceID we are searching
//                if (resourceID == i.getInt(null)) return i.name // return the name
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//        // Throw exception if nothing was found
//        throw IllegalArgumentException()
//    }
//
//}