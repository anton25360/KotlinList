package com.anton25360.kotlinlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_popular_item_detail.*

class PopularItemDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_item_detail)

        //get the clicked item
        val dataFromIntent = intent.getStringArrayListExtra("chosenItem")
        val item = dataFromIntent?.get(0)

        popular_item_detail_textView.text = "$item" //display clicked item
    }
}