package com.anton25360.kotlinlist.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.anton25360.kotlinlist.DatabaseHandler
import com.anton25360.kotlinlist.R
import com.anton25360.kotlinlist.adapters.PopularItemsAdapter
import com.anton25360.kotlinlist.adapters.ShoppingListAdapter
import kotlinx.android.synthetic.main.fragment_popular_items.*
import kotlinx.android.synthetic.main.fragment_shopping_list.*

class ShoppingListFragment : Fragment() {

    var db = DatabaseHandler(requireContext())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shopping_list_recyclerView.layoutManager = LinearLayoutManager(requireContext()) //set layout manager
        populateList()
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    fun populateList() {
        var data = db.readDatafromDB()
        shopping_list_recyclerView.adapter = ShoppingListAdapter(data)

    }


}