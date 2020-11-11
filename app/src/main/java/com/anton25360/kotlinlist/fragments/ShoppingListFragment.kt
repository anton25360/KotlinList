package com.anton25360.kotlinlist.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.anton25360.kotlinlist.DatabaseHandler
import com.anton25360.kotlinlist.R
import com.anton25360.kotlinlist.adapters.OnItemClickListener
import com.anton25360.kotlinlist.adapters.OnItemClickListener2
import com.anton25360.kotlinlist.adapters.ShoppingListAdapter
import kotlinx.android.synthetic.main.fragment_shopping_list.*

class ShoppingListFragment : Fragment(), OnItemClickListener2 {

//    var db = DatabaseHandler(requireContext())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        shopping_list_recyclerView.layoutManager = LinearLayoutManager(requireContext()) //set layout manager
        populateList()
    }

    fun populateList() {
        val data = DatabaseHandler(requireContext()).readDataFromDB()
        shopping_list_recyclerView.adapter = ShoppingListAdapter(data,this@ShoppingListFragment)
    }

    override fun onItemClick(position: Int) {

        Toast.makeText(requireContext(), "helo", Toast.LENGTH_SHORT).show()
    }


}