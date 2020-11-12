package com.anton25360.kotlinlist.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.anton25360.kotlinlist.DatabaseHandler
import com.anton25360.kotlinlist.PopularItemDetailActivity
import com.anton25360.kotlinlist.R
import com.anton25360.kotlinlist.ShoppingListNewItemActivity
import com.anton25360.kotlinlist.adapters.OnItemClickListener2
import com.anton25360.kotlinlist.adapters.ShoppingListAdapter
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import java.util.*
import kotlin.collections.ArrayList

class ShoppingListFragment : Fragment(), OnItemClickListener2 {

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

        //FAB trigger
        shopping_list_FAB.setOnClickListener(){
            openNewItemActivity()
        }

        shopping_list_SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener { //searchView listener

            override fun onQueryTextSubmit(query: String?): Boolean {

                //checks for duplicate in db
                val arrayOfResults = ArrayList<Any>() //empty array, to store compatible strings
                val data: ArrayList<Any> = DatabaseHandler(requireContext()).readDataFromDB() //gets data from db
                for (itemCompare in data) {
                    val stringInDBPlusAmount: ArrayList<Any> = itemCompare as ArrayList<Any> //item from the db
                    val stringInDB: String = stringInDBPlusAmount[0] as String //string from the item

                    if (stringInDB.toLowerCase().indexOf(query.toString().toLowerCase()) != -1) { //if searched string is in db (eg: ST in STEAK) then put STEAK in arrayOfResults
                        Toast.makeText(requireContext(), "found $query in $stringInDB", Toast.LENGTH_SHORT).show()
                        arrayOfResults.add(stringInDBPlusAmount) //put STEAK in arrayOfResults
                    }
                }
                shopping_list_recyclerView.adapter = ShoppingListAdapter(arrayOfResults,this@ShoppingListFragment) //set new adapter with search results
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                return true
            }

        })

    }

    override fun onResume() {
        super.onResume()
        populateList()
    }

    fun populateList() {
        val data = DatabaseHandler(requireContext()).readDataFromDB()
        shopping_list_recyclerView.adapter = ShoppingListAdapter(data,this@ShoppingListFragment)
    }

    override fun onItemClick(position: Int) {
        DatabaseHandler(requireContext()).deleteDataFromDB(position)
        populateList()
    }

    private fun openNewItemActivity() {
        val intent = Intent(requireContext(), ShoppingListNewItemActivity::class.java)
        startActivity(intent)
    }
}