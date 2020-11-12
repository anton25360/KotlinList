package com.anton25360.kotlinlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.anton25360.kotlinlist.fragments.ShoppingListFragment
import kotlinx.android.synthetic.main.activity_popular_item_detail.*
import kotlinx.android.synthetic.main.activity_shopping_list_new_item.*
import kotlin.math.log

class ShoppingListNewItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list_new_item)
    }

    fun addToDB(view: View) {
        val name = shopping_list_new_item_inputName.text.toString() //name entered
        val quantity = shopping_list_new_item_inputAmount.text.toString() //number entered

        //checks for duplicate in db
        val arrayOfTruth = ArrayList<Any>()
        val data :ArrayList<Any> = DatabaseHandler(this).readDataFromDB()
        for (itemCompare in data){
            val bool = name in itemCompare.toString()
            arrayOfTruth.add(bool.toString())
        }

        if (shopping_list_new_item_inputAmount.length() == 0 || quantity == "0"){ //if quantity input is empty or the number 0, show error message
            shopping_list_new_item_inputAmount.setError("Must not be empty") //set error msg

        }else if (shopping_list_new_item_inputName.length() == 0){ //if name input is empty, show error message
            shopping_list_new_item_inputName.setError("Must not be empty") //set error msg

        } else if (arrayOfTruth.contains("true")) { //if name already exists in db
            shopping_list_new_item_inputName.setError("Already in shopping list!") //set erorr msg

        } else { //else, add data to DB and refresh shopping list adapter
                DatabaseHandler(this).insertDataIntoDB(name, quantity.toInt()) //add to db
                shopping_list_new_item_inputName.text.clear() //clear input
                shopping_list_new_item_inputAmount.text.clear() //clear input
                Toast.makeText(this, "close activity now", Toast.LENGTH_SHORT).show()
        }
    }
}