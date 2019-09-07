package com.ishiki.mizuwodrinkwater.unused
//
//import android.content.Context
//import android.widget.ImageView
//import com.ishiki.mizuwodrinkwater.R
//import com.ishiki.mizuwodrinkwater.activities.MainActivity
//import com.ishiki.mizuwodrinkwater.model.Drinks
////import com.ishiki.mizuwodrinkwater.services.DrinksToday.sharedPreferences
//
//object DrinkTypes {
//
//    // Must probably be a mutable list
////    val glasses = arrayListOf<Drinks>(
////        Drinks("water01", 250),
////        Drinks("water02", 500),
////        Drinks("water03", 150)
////    )
//
////    fun saveEditedDrink(editedVolume: Int, editedImage: String): Drinks {
////        val editedDrink = Drinks(editedImage, editedVolume)
//////                glasses[adapterPosition] = editedDrink
////
//////                glassesList[adapterPosition] = editedDrink
////        return editedDrink
////    }
//
////    fun nameToResourceId(number: Int, context: Context): Int {
////        val name = "water0$number"
////        return context.resources.getIdentifier(name, "drawable", context.packageName)
////    }
//
////    val drinks = mutableListOf<Drinks>(
////        Drinks("water01", "250", "ml"),
////        Drinks("water02", "500", "ml"),
////        Drinks("water03", "150", "ml")
////    )
//
////    var currentGlass = drinks[0]
//
////    fun serializeCustomDrinksList() {
////        val image: ArrayList<String> = ArrayList()
////        val volume: ArrayList<String> = ArrayList()
////        val unit: ArrayList<String> = ArrayList()
////
////        for (drinks: Drinks in drinks) {
////            image.add(drinks.image)
////            volume.add(drinks.volume)
////            unit.add(drinks.unit)
////        }
////
////        sharedPreferences?.edit()?.putString("imageCustom", ObjectSerializer.serialize(image))?.apply()
////        sharedPreferences?.edit()?.putString("volumeCustom", ObjectSerializer.serialize(volume))?.apply()
////        sharedPreferences?.edit()?.putString("unitCustom", ObjectSerializer.serialize(unit))?.apply()
////    }
//}