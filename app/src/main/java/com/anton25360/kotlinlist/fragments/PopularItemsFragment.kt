package com.anton25360.kotlinlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.anton25360.kotlinlist.MainActivity
import com.anton25360.kotlinlist.R
import com.anton25360.kotlinlist.fragments.adapters.OnItemClickListener
import com.anton25360.kotlinlist.fragments.adapters.PopularItemsAdapter
import kotlinx.android.synthetic.main.fragment_popular_items.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class PopularItemsFragment : Fragment(), OnItemClickListener {

    val availableItems = arrayListOf<Any>() //create empty array to store items that are 'available'

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_popular_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        popular_item_recyclerView.layoutManager = LinearLayoutManager(requireContext()) //set layout manager
        fetchJSon() //get data and populate list
    }

    fun fetchJSon(){

        //values
        val url = "https://popular-items.azurewebsites.net/popular_items.json"
        val request = Request.Builder().url(url).build()

        OkHttpClient().newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val data: String? = response.body?.string() //response as a string
                val jsonArray = JSONArray(data) //convert to json array

                //loop through original json array:
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)

                    if (item.get("available") == "yes") { //if item is available
                        availableItems.add(item.get("item_name")) //add it to the array
                    }
                }

                activity?.runOnUiThread(Runnable {
                    popular_item_recyclerView.adapter = PopularItemsAdapter(availableItems, this@PopularItemsFragment)
                })

            }

            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute request: " + e)
            }
        })
    }

    override fun onItemClick(position: Int) {
//        Toast.makeText(requireContext(), "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = availableItems[position]
        Toast.makeText(requireContext(), "you just clicked $clickedItem", Toast.LENGTH_SHORT).show()
//        popular_item_recyclerView.adapter?.notifyItemChanged(position)
    }
}

