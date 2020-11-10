package com.anton25360.kotlinlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_popular_item_detail.*

class PopularItemDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_item_detail)

        val dataFromIntent = intent.getStringArrayListExtra("chosenItem") //data from intent
        val item = dataFromIntent?.get(0) //get the name of the clicked item
        popular_item_detail_textView.text = "$item" //set item text

//        popular_item_detail_button.setOnClickListener {toastMe()} //run function on btn click
    }


    fun toastMe(view: View) {
        val dataFromIntent = intent.getStringArrayListExtra("chosenItem") //data from intent
        val item = dataFromIntent?.get(0) //get the name of the clicked item
        val quantity = popular_item_detail_inputField.text.toString()

        if (popular_item_detail_inputField.length() == 0 || quantity == "0"){
            popular_item_detail_inputField.setError("Must not be empty")
        } else {
            Toast.makeText(this, "Add $item x $quantity", Toast.LENGTH_SHORT).show()
        }
    }
}