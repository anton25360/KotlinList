package com.anton25360.kotlinlist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import com.anton25360.kotlinlist.MainActivity
import com.anton25360.kotlinlist.R
import com.anton25360.kotlinlist.fragments.adapters.MainAdapter
import kotlinx.android.synthetic.main.fragment_popular_items.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException


class PopularItemsFragment : Fragment() {


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

        val words = arrayOf("one", "two", "three")

        popular_item_recyclerView.layoutManager = LinearLayoutManager(requireContext())
//        popular_item_recyclerView.adapter = MainAdapter(words)


        fetchJSon()

    }

    fun fetchJSon(){

        val url = "https://popular-items.azurewebsites.net/popular_items.json"
        val request = Request.Builder().url(url).build()
        val client = OkHttpClient()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val data: String? = response.body?.string() //response as a string
                val jsonArray = JSONArray(data) //convert to json array
                val availableItems =
                    arrayListOf<Any>() //create empty array to store items that are 'available'

                //loop through original json array:
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)

                    if (item.get("available") == "yes") { //if item is available
                        availableItems.add(item.get("item_name")) //add it to the array
                    }
                }
                println(availableItems)
//                popular_item_recyclerView.adapter = MainAdapter(availableItems)

                activity?.runOnUiThread(Runnable {
                    popular_item_recyclerView.adapter = MainAdapter(availableItems)
                })

            }

            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute request: " + e)
            }
        })
    }
}

