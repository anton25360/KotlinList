package com.anton25360.kotlinlist.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.anton25360.kotlinlist.R
import kotlinx.android.synthetic.main.fragment_popular_items.*
import okhttp3.*
import org.json.JSONObject
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
        piList.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, words)


        fetchJSon()

//        Log.d(TAG, apiResponse)

    }

    fun populateList(){
        Log.d(TAG, "populating listView...")
        val words = arrayOf("one", "two", "three")

        for (word in words) {
            Log.d(TAG, "populateList: $word")
        }
    }

    fun fetchJSon(){

        val url = "https://popular-items.azurewebsites.net/popular_items.json"
        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
//                val jsonObject = JSONObject(body)
                println(body)

            }

            override fun onFailure(call: Call, e: IOException) {
                println("failed to execute request: " + e)
            }
        })
    }
}
