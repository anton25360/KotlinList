package com.anton25360.kotlinlist.fragments

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.anton25360.kotlinlist.R
import kotlinx.android.synthetic.main.fragment_popular_items.*

class PopularItemsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        populateList()
//        val words = arrayOf("one", "two", "three")
//        piList.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,words)



    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
        val words = arrayOf("one", "two", "three")
        piList.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,words)

    }

    fun populateList(){
        Log.d(TAG, "populating listView...")
        val words = arrayOf("one", "two", "three")

        for (word in words) {
            Log.d(TAG, "populateList: $word")
        }
    }

}
