package com.anton25360.kotlinlist.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.fragment.app.Fragment
import com.anton25360.kotlinlist.R
import kotlinx.android.synthetic.main.fragment_popular_items.*


class PopularItemsFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        populateList()

        piList.adapter = ???

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_items, container, false)

    }

    fun populateList(){
        Log.d(TAG, "populating listView...")
        val words = arrayOf("one", "two", "three")

        for (word in words) {
            Log.d(TAG, "populateList: $word")
        }
    }

    private class customAdapter: BaseAdapter {

        override fun getCount(): Int {
            TODO("Not yet implemented")
        }

        override fun getItemId(p0: Int): Long {
            TODO("Not yet implemented")
        }

        override fun getItem(p0: Int): Any {
            TODO("Not yet implemented")
        }

        //gets the row
        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            TODO("Not yet implemented")
        }
    }


}
