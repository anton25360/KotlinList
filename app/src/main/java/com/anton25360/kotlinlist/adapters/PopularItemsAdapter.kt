package com.anton25360.kotlinlist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anton25360.kotlinlist.R
import kotlinx.android.synthetic.main.popular_item_row.view.*

class PopularItemsAdapter(val array: ArrayList<Any>, val listener: OnItemClickListener): RecyclerView.Adapter<CustomViewHolder>() {

//    val words = arrayOf("one", "two", "three")

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.popular_item_row, parent, false)
        return CustomViewHolder(cellForRow, listener)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.view.popular_item_textView.text = array[position] as CharSequence?
    }
}

class CustomViewHolder(val view: View, val listener: OnItemClickListener): RecyclerView.ViewHolder(view),
View.OnClickListener{
    init {
        view.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        //gets position of item
        val position: Int = adapterPosition
        if (position != RecyclerView.NO_POSITION) { //ensure the position is valid
            listener.onItemClick(position)
        }
    }
}

interface  OnItemClickListener {
    fun onItemClick(position: Int)


}