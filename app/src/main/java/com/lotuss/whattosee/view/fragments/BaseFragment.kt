package com.lotuss.whattosee.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.lotuss.whattosee.R
import com.lotuss.whattosee.view.adapters.MovieAdapter
import com.lotuss.whattosee.viewmodels.MoviesViewModel

open class BaseFragment: Fragment(), SearchView.OnQueryTextListener {

    lateinit var viewModel: MoviesViewModel
    lateinit var adapter: MovieAdapter

    private lateinit var searchView: SearchView
    private lateinit var menuItem: MenuItem
    private lateinit var optionsItem: MenuItem
    private var searchText: String = ""

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

        if (searchText.isNotEmpty()) {
            menuItem.expandActionView()
            searchView.setQuery(searchText, true)
            searchView.isIconified = false
        }

        optionsItem = menu.findItem(R.id.options)
        optionsItem.setOnMenuItemClickListener {true}
        super.onCreateOptionsMenu(menu, inflater)
    }


}