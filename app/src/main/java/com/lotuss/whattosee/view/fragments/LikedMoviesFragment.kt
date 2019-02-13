package com.lotuss.whattosee.view.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lotuss.whattosee.R
import com.lotuss.whattosee.data.model.MovieModel
import com.lotuss.whattosee.view.adapters.MovieAdapter
import com.lotuss.whattosee.viewmodels.MoviesViewModel

import kotlinx.android.synthetic.main.liked_movies_fragment.*
import kotlinx.android.synthetic.main.liked_movies_fragment.view.*

class LikedMoviesFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.liked_movies_fragment, container, false)
        if (activity!!.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            view.liked_recycler_view.layoutManager = GridLayoutManager(activity, 2)
        else view.liked_recycler_view.layoutManager = GridLayoutManager(activity, 3)
        view.liked_recycler_view.setHasFixedSize(true)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel(activity!!.application)::class.java)
        adapter = MovieAdapter(viewModel, false)
        liked_recycler_view.adapter = adapter
        viewModel.getMovies().observe(this, Observer<List<MovieModel>> {
            val list: List<MovieModel> = it!!.filter {movie -> movie.isLiked }
            adapter.setMovies(list)
            showEmptyLayout(list.isEmpty())
        })
        viewModel.searchText.observe(this, Observer<String> {
            adapter.searchMovies(it!!)
        })
    }

    private fun showEmptyLayout(show: Boolean) {
        if (show)
            empty_liked_text.visibility = View.VISIBLE
        else empty_liked_text.visibility = View.GONE
    }
}
