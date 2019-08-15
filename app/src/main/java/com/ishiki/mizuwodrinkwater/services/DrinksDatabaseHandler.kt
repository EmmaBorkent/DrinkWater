package com.ishiki.mizuwodrinkwater.services

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.ishiki.mizuwodrinkwater.model.Drinks
import com.ishiki.mizuwodrinkwater.utilities.*

class DrinksDatabaseHandler(context: Context) :
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createDrinksTable = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_DRINK_IMAGE + " TEXT," +
                KEY_DRINK_VOLUME + " INT," +
                KEY_DRINK_TIME + " LONG" + ")"

        db?.execSQL(createDrinksTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // CRUD - Create, Read, Update, Delete

    fun createDrink(drink: Drinks) {
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()
        values.put(KEY_DRINK_IMAGE, drink.image)
        values.put(KEY_DRINK_VOLUME, drink.volume)
        values.put(KEY_DRINK_TIME, System.currentTimeMillis())

        db.insert(TABLE_NAME, null, values)

        Log.d("DATA INSERTED", "SUCCESS")
        db.close()
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
        val drink = Drinks(cursor.getString(cursor.getColumnIndex(KEY_DRINK_IMAGE)),
            cursor.getInt(cursor.getColumnIndex(KEY_DRINK_VOLUME)))
//        drink.image =
//        drink.volume =
        drink.time = cursor.getLong(cursor.getColumnIndex(KEY_DRINK_TIME))

        cursor.close()
        return drink
    }

    fun readAllDrinks(): ArrayList<Drinks> {
        val db = readableDatabase
        val list: ArrayList<Drinks> = ArrayList()
        val selectAll = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectAll, null)

        if (cursor.moveToFirst()) {
            do {
                val drink = Drinks(cursor.getString(cursor.getColumnIndex(KEY_DRINK_IMAGE)),
                    cursor.getInt(cursor.getColumnIndex(KEY_DRINK_VOLUME)))
                drink.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
//                drink.image =
//                drink.volume =
                drink.time = cursor.getLong(cursor.getColumnIndex(KEY_DRINK_TIME))

                list.add(drink)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return list
    }

    fun updateDrink(drink: Drinks): Int {
        val db = writableDatabase
        val values = ContentValues()
        values.put(KEY_DRINK_IMAGE, drink.image)
        values.put(KEY_DRINK_VOLUME, drink.volume)
        values.put(KEY_DRINK_TIME, System.currentTimeMillis())

        return db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(drink.id.toString()))
    }

    fun deleteDrink(id: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(id.toString()))
        db.close()
    }

    fun getDrinksCount(): Int {
        val db = readableDatabase
        val countQuery = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(countQuery, null)
        cursor.close()
        return cursor.count
    }

}