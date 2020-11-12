package com.anton25360.kotlinlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.anton25360.kotlinlist.fragments.ShoppingListFragment
import kotlinx.android.synthetic.main.activity_popular_item_detail.*
import kotlin.math.log

class ShoppingListNewItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_item_detail)

        val dataFromIntent = intent.getStringArrayListExtra("chosenItem") //data from intent
        val item = dataFromIntent?.get(0) //get the name of the clicked item
        popular_item_detail_textView.text = "$item" //set item text

    }

    fun checkIfNameExists(name:String) {
        val arrayOfTruth = ArrayList<Any>()
        val data :ArrayList<Any> = DatabaseHandler(this).readDataFromDB()
        for (item in data){
            val bool = name in item.toString()
            arrayOfTruth.add(bool)
        }
    }

    fun addToDB(view: View) {
        val dataFromIntent = intent.getStringArrayListExtra("chosenItem") //data from intent
        val item = dataFromIntent?.get(0) //get the name of the clicked item
        val quantity = popular_item_detail_inputField.text.toString() //number entered

        //checks for duplicate in db
        val arrayOfTruth = ArrayList<Any>()
        val data :ArrayList<Any> = DatabaseHandler(this).readDataFromDB()
        for (itemCompare in data){
            val bool = item.toString() in itemCompare.toString()
            arrayOfTruth.add(bool.toString())
        }

        //if input is null or 0, show error message
        if (popular_item_detail_inputField.length() == 0 || quantity == "0"){
            popular_item_detail_inputField.setError("Must not be empty")

        } else if (arrayOfTruth.contains("true")) {
            popular_item_detail_inputField.setError("Already in shopping list!")
            Toast.makeText(this, "STOP!!!", Toast.LENGTH_SHORT).show()

        }else {
                //else, add data to DB and refresh shopping list adapter
                DatabaseHandler(this).insertDataIntoDB(item.toString(), quantity.toInt())
                popular_item_detail_inputField.text.clear()
        }
    }
}