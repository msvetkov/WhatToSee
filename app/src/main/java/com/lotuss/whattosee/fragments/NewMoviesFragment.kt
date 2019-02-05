package com.lotuss.whattosee.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lotuss.whattosee.R
import com.lotuss.whattosee.adapters.MovieAdapter
import com.lotuss.whattosee.data.model.MovieModel
import com.lotuss.whattosee.viewmodels.NewMoviesViewModel
import kotlinx.android.synthetic.main.new_movies_fragment.view.*

class NewMoviesFragment : Fragment() {

    private val adapter = MovieAdapter(1)

    private lateinit var viewModel: NewMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.new_movies_fragment, container, false)
        view.new_recycler_view.layoutManager = LinearLayoutManager(activity)
        view.new_recycler_view.setHasFixedSize(true)
        view.new_recycler_view.adapter = adapter
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewMoviesViewModel(activity!!.application)::class.java)
        viewModel.loadNewMovies()
        viewModel.getNewMovies().observe(this, Observer<List<MovieModel>> {
            if (it != null) {
                adapter.setNotes(it)

            }
        })
    }
}
