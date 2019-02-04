package com.lotuss.whattosee.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lotuss.whattosee.R
import com.lotuss.whattosee.viewmodel.NewMoviesViewModel

class NewMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = NewMoviesFragment()
    }

    private lateinit var viewModel: NewMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.new_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
