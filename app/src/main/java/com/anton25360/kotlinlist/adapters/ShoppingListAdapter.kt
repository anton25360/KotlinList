package com.anton25360.kotlinlist.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.anton25360.kotlinlist.DatabaseHandler
import com.anton25360.kotlinlist.R
import kotlinx.android.synthetic.main.popular_item_row.view.*
import kotlinx.android.synthetic.main.shopping_list_row.view.*

class ShoppingListAdapter(val array: ArrayList<Any>): RecyclerView.Adapter<CustomViewHolder2>() {

    override fun getItemCount(): Int {
        return array.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder2{
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.shopping_list_row, parent, false)
        return CustomViewHolder2(cellForRow)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CustomViewHolder2, position: Int) {
        val array = array[position] as ArrayList<*>

        holder.view.shopping_list_textViewName.text = array.first().toString()
        holder.view.shopping_list_textViewAmount.text ="x" + array.last().toString()

    }

}

class CustomViewHolder2(val view: View): RecyclerView.ViewHolder(view) {
}