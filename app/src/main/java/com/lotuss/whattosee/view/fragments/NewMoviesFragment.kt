package com.lotuss.whattosee.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.*

import com.lotuss.whattosee.R
import com.lotuss.whattosee.view.adapters.MovieAdapter
import com.lotuss.whattosee.data.model.MovieModel
import com.lotuss.whattosee.viewmodels.MoviesViewModel
import kotlinx.android.synthetic.main.new_movies_fragment.*
import kotlinx.android.synthetic.main.new_movies_fragment.view.*

class NewMoviesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.new_movies_fragment, container, false)
        if (activity!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            view.new_recycler_view.layoutManager = GridLayoutManager(activity, 2)
        else view.new_recycler_view.layoutManager = GridLayoutManager(activity, 3)
        view.new_recycler_view.setHasFixedSize(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =  ViewModelProviders.of(this).get(MoviesViewModel(activity!!.application)::class.java)
        adapter = MovieAdapter(viewModel, true)
        new_recycler_view.adapter = adapter
        viewModel.getMovies().observe(this, Observer<List<MovieModel>> {
            adapter.setMovies(it!!)
            showProgress(it.isEmpty())
        })
        viewModel.searchText.observe(this, Observer<String> {
            adapter.searchMovies(it!!)
        })
    }

    private fun showProgress(show: Boolean) {
        if (show)
            progress.visibility = View.VISIBLE
        else  progress.visibility = View.GONE
    }
}
