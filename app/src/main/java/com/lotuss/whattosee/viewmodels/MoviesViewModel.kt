package com.lotuss.whattosee.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.lotuss.whattosee.data.MovieProvider
import com.lotuss.whattosee.data.model.MovieModel

class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val movieProvider = MovieProvider()
    val searchText = MutableLiveData<String>()

    init {
        searchText.value = ""
    }

    fun getMovies(): LiveData<List<MovieModel>> {
        return movieProvider.getMovies()
    }

    fun updateMovieStatus(movie: MovieModel) {
        movieProvider.addToLiked(movie)
    }

    fun getMovieById(movieId: Long): LiveData<MovieModel> {
        return movieProvider.getById(movieId)
    }
}