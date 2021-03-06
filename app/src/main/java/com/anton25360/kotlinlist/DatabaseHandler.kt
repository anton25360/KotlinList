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

@Suppress("UNCHECKED_CAST")
class DatabaseHandler(var context: Context) :SQLiteOpenHelper(context, "db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_QUANTITY + " INTEGER NOT NULL );"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertDataIntoDB(name: String, amount: Int){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_NAME, name)
        cv.put(COLUMN_QUANTITY, amount)
        val result = db.insert(TABLE_NAME, null, cv)

        if (result == (-1).toLong())
            Toast.makeText(context, "Failed, please try again", Toast.LENGTH_SHORT).show()
        else
//            Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }

    fun readDataFromDB() : ArrayList<Any>{
        val list: ArrayList<Any> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) { //returns true if cursor is not null
            do {
                val name = result.getString(result.getColumnIndex(COLUMN_NAME))
                val quantity = result.getString(result.getColumnIndex(COLUMN_QUANTITY)).toInt()
                val innerArray = arrayListOf<Any>(name, quantity)
                list.add(innerArray)
            }while ( result.moveToNext())
        }

        result.close() //close cursor
        db.close() //close cursor
        return list
    }

    fun deleteDataFromDB(position: Int){
        val data = readDataFromDB()
        val item:ArrayList<Any> = data[position] as ArrayList<Any>
        val name = item.first().toString()
        val db = this.readableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE $COLUMN_NAME='$name'")
        db.close()
    }
}