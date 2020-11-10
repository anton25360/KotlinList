package com.anton25360.kotlinlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anton25360.kotlinlist.R
import kotlinx.android.synthetic.main.popular_item_row.view.*

class ShoppingListAdapter(val array: ArrayList<Any>): RecyclerView.Adapter<CustomViewHolder2>() {

    override fun getItemCount(): Int {
        return array.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder2{
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.shopping_list_row, parent, false)
        return CustomViewHolder2(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder2, position: Int) {
        holder.view.popular_item_textView.text = array[position] as CharSequence?
    }
}

class CustomViewHolder2(val view: View): RecyclerView.ViewHolder(view) {

}