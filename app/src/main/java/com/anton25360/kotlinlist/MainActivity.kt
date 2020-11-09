package com.anton25360.kotlinlist

import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import com.anton25360.kotlinlist.fragments.PopularItemsFragment
import com.anton25360.kotlinlist.fragments.ShoppingListFragment
import com.anton25360.kotlinlist.fragments.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_main)

        setUpTabs()
    }

    private fun setUpTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(PopularItemsFragment(), "Popular Items")
        adapter.addFragment(ShoppingListFragment(), "Shopping List")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


    }
}