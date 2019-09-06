package com.ishiki.mizuwodrinkwater.model

import android.content.Context

class Glasses(var image: String, var volume: Int) {

    var number = image[6].toString().toInt()

    companion object {

        val glassesList = arrayListOf(
            Glasses("water01", 250),
            Glasses("water02", 500),
            Glasses("water03", 150)
        )

        fun imageNameToResourceId(number: Int, context: Context): Int {
            val imageName = "water0$number"
            return context.resources.getIdentifier(imageName, "drawable", context.packageName)
        }

        // CRUD - create, read, update, delete
        // Functions for ArrayList: addAll, clear, contains, equals, get, indexOf, remove, removeAll

        fun createGlass(image: String, volume: Int) {
            glassesList.add(0, Glasses(image, volume))
        }

        fun readGlass(position: Int) {
            glassesList[position]
        }

        fun updateGlass(image: String, volume: Int, position: Int) {
            val changedGlass = Glasses(image, volume)
            glassesList[position] = changedGlass
        }

        fun deleteGlass(position: Int) {
            glassesList.removeAt(position)
        }
    }
}