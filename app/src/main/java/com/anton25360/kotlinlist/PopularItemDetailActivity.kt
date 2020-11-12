package com.anton25360.kotlinlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_popular_item_detail.*

class PopularItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_item_detail)

        val dataFromIntent = intent.getStringArrayListExtra("chosenItem") //data from intent
        val item = dataFromIntent?.get(0) //get the name of the clicked item
        popular_item_detail_textView.text = "$item" //set item text

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

        if (popular_item_detail_inputField.length() == 0 || quantity == "0"){ //if input is null or 0, show error message
            popular_item_detail_inputField.setError("Must not be empty") //set error msg

        } else if (arrayOfTruth.contains("true")) { //if name already exists in db
            popular_item_detail_inputField.setError("Already in shopping list!") //set error msg

        }else { //else, add data to DB
                DatabaseHandler(this).insertDataIntoDB(item.toString(), quantity.toInt()) //add to db
                popular_item_detail_inputField.text.clear() //clear input field
        }
    }
}