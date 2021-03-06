package com.anton25360.kotlinlist.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.anton25360.kotlinlist.PopularItemDetailActivity
import com.anton25360.kotlinlist.R
import com.anton25360.kotlinlist.adapters.OnItemClickListener
import com.anton25360.kotlinlist.adapters.PopularItemsAdapter
import kotlinx.android.synthetic.main.fragment_popular_items.*
import okhttp3.*
import org.json.JSONArray
import java.io.IOException

class PopularItemsFragment : Fragment(), OnItemClickListener {

    val availableItems = arrayListOf<Any>() //create empty array to store items that are 'available'

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

    private fun fetchJSon(){

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

                activity?.runOnUiThread {
                    popular_item_recyclerView.adapter = PopularItemsAdapter(availableItems, this@PopularItemsFragment)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                println(e)
            }
        })
    }

    override fun onItemClick(position: Int) {
        val clickedItem = availableItems[position]
        openDetailFragment(clickedItem)
    }

    private fun openDetailFragment(item:Any) {
        val intent = Intent(requireContext(), PopularItemDetailActivity::class.java)
        val chosenItem = arrayListOf(item)
        intent.putExtra("chosenItem",chosenItem)
        startActivity(intent)

    }


}

