package com.lotuss.whattosee.view.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.lotuss.whattosee.R
import com.lotuss.whattosee.view.fragments.LikedMoviesFragment
import com.lotuss.whattosee.view.fragments.NewMoviesFragment


class SimpleFragmentPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            NewMoviesFragment()
        } else
            LikedMoviesFragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return when (position) {
            0 -> mContext.getString(R.string.category_new_movies)
            1 -> mContext.getString(R.string.category_liked_movies)
            else -> null
        }
    }

}