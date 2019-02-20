package com.lotuss.whattosee.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.lotuss.whattosee.R
import com.lotuss.whattosee.views.adapters.MovieAdapter
import com.lotuss.whattosee.viewmodel.MoviesViewModel

open class BaseFragment: Fragment(), SearchView.OnQueryTextListener {

    lateinit var viewModel: MoviesViewModel
    lateinit var adapter: MovieAdapter

    private lateinit var searchView: SearchView
    private lateinit var menuItem: MenuItem

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(text: String?): Boolean {
        viewModel.searchText.value = text!!
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.movies_menu, menu)
        menuItem = menu!!.findItem(R.id.search)
        searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        if (viewModel.searchText.value!!.isNotEmpty()) {
            menuItem.expandActionView()
            searchView.setQuery(viewModel.searchText.value!!, true)
            searchView.isIconified = false
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun showErrorMessage(isConnect: Boolean) {
        if (!isConnect)
            Toast.makeText(context, "Unable to load the data. Please check your network connection.", Toast.LENGTH_LONG)
                .show()
    }
}