package com.anton25360.kotlinlist.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anton25360.kotlinlist.R
import kotlinx.android.synthetic.main.popular_item_row.view.*

class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    val words = arrayOf("one", "two", "three")

    override fun getItemCount(): Int {
        return words.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.popular_item_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.popular_item_textView.text = words[position]
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}