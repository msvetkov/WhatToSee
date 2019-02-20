package com.lotuss.whattosee.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.lotuss.whattosee.R
import com.lotuss.whattosee.views.adapters.SimpleFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = this.toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "Movies"

        val adapter = SimpleFragmentPagerAdapter(this, supportFragmentManager)
        viewpager.adapter = adapter
        sliding_tabs.setupWithViewPager(viewpager)
    }
}
