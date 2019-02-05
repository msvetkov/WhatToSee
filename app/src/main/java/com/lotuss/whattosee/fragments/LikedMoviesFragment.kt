package com.lotuss.whattosee.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lotuss.whattosee.R
import com.lotuss.whattosee.viewmodels.LikedMoviesViewModel

class LikedMoviesFragment : Fragment() {



    //private lateinit var viewModel: LikedMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.liked_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProviders.of(this).get(LikedMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
