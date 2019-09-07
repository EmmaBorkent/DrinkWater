package com.ishiki.mizuwodrinkwater.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ishiki.mizuwodrinkwater.model.Drinks

class DrinksDatabaseHandler(context: Context) :
        SQLiteOpenHelper(context,
            DATABASE_NAME, null,
            DATABASE_VERSION
        ) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createDrinksTable = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_DRINK_IMAGE + " TEXT," +
                KEY_DRINK_VOLUME + " INT," +
                KEY_DRINK_TIME + " LONG," + ")"

        db?.execSQL(createDrinksTable)
        Log.d("DrinksDatabaseHandler", "Database Table Created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
        Log.d("DrinksDatabaseHandler", "Database Upgraded")
    }

    // CRUD - Create, Read, Update, Delete

    fun createDrink(drink: Drinks) {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()
        values.put(KEY_DRINK_IMAGE, drink.image)
        values.put(KEY_DRINK_VOLUME, drink.volume)
        values.put(KEY_DRINK_TIME, System.currentTimeMillis())

        db.insert(TABLE_NAME, null, values)
        db.close()

        Log.d("DrinksDatabaseHandler", "Created New Drink")
    }

    fun readDrink(id: Int): Drinks {
        val db: SQLiteDatabase = writableDatabase
        val cursor = db.query(
            TABLE_NAME,
                arrayOf(
                    KEY_ID,
                    KEY_DRINK_IMAGE,
                    KEY_DRINK_VOLUME,
                    KEY_DRINK_TIME
                ), "$KEY_ID=?",
                arrayOf(id.toString()),
                null, null, null, null
        )

        cursor?.moveToFirst()
        val drink = Drinks()
        drink.image = cursor.getString(cursor.getColumnIndex(KEY_DRINK_IMAGE))
        drink.volume = cursor.getInt(cursor.getColumnIndex(KEY_DRINK_VOLUME))
        drink.time = cursor.getLong(cursor.getColumnIndex(KEY_DRINK_TIME))

        cursor.close()
        Log.d("DrinksDatabaseHandler", "Read a Drink from Database")
        return drink
    }

    fun readAllDrinks(): ArrayList<Drinks> {
        val db = readableDatabase
        val list: ArrayList<Drinks> = ArrayList()
        val selectAll = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectAll, null)

        if (cursor.moveToFirst()) {
            do {
                val drink = Drinks()
                drink.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                drink.image = cursor.getString(cursor.getColumnIndex(KEY_DRINK_IMAGE))
                drink.volume = cursor.getInt(cursor.getColumnIndex(KEY_DRINK_VOLUME))
                drink.time = cursor.getLong(cursor.getColumnIndex(KEY_DRINK_TIME))

                list.add(drink)
            } while (cursor.moveToNext())
        }

        cursor.close()
        Log.d("DrinksDatabaseHandler", "Read a List of Drinks from Database")
        return list
    }

    fun updateDrink(drink: Drinks): Int {
        val db = writableDatabase
        val values = ContentValues()
        values.put(KEY_DRINK_IMAGE, drink.image)
        values.put(KEY_DRINK_VOLUME, drink.volume)
        values.put(KEY_DRINK_TIME, System.currentTimeMillis())
        Log.d("DrinksDatabaseHandler", "Changed a Drink")
        return db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(drink.id.toString()))
    }

    fun deleteDrink(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(id.toString()))
        db.close()
        Log.d("DrinksDatabaseHandler", "Deleted a Drink")
    }

    fun getCount(): Int {
        val db = readableDatabase
        val countQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(countQuery, null)
        Log.d("DrinksDatabaseHandler", "There are ${cursor.count} items in Database")
        cursor.close()
        return cursor.count
    }
}

//    fun findDay(time: Long): Drinks {
//        val db: SQLiteDatabase = writableDatabase
//        val query = "SELECT * FROM $TABLE_NAME WHERE $KEY_DRINK_TIME = \"$time\""
//        val cursor = db.rawQuery(query, null)
//        lateinit var drink: Drinks
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst()
//            drink = Drinks(
//                cursor.getString(cursor.getColumnIndex(KEY_DRINK_IMAGE)),
//                cursor.getInt(cursor.getColumnIndex(KEY_DRINK_VOLUME)),
//                cursor.getInt(cursor.getColumnIndex(KEY_DRINK_GOAL))
//            )
////            drink.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_ID)))
//            drink.time = cursor.getLong(cursor.getColumnIndex(KEY_DRINK_TIME))
//            cursor.close()
//        }
//        db.close()
//        Log.d("DrinksDatabaseHandler", "Found a Drink in the Database at $time")
//        return drink
//    }