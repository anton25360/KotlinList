package com.anton25360.kotlinlist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns._ID
import android.widget.Toast

//val DATABASE_NAME = "db"
const val TABLE_NAME = "shoppingList"
const val COLUMN_NAME = "name"
const val COLUMN_QUANTITY = "quantity"

class DatabaseHandler (var context: Context) :SQLiteOpenHelper(context, "db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_QUANTITY + " INTEGER NOT NULL );";
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }


    fun insertDataIntoDB(name:String, amount:Int){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME, name)
        cv.put(COLUMN_QUANTITY, amount)
        val result = db.insert(TABLE_NAME, null, cv)

//        Toast.makeText(context, "DONE", Toast.LENGTH_SHORT).show()
        if (result == -1.toLong())
            Toast.makeText(context, "Failed, please try again", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
    }
}