package com.lotuss.whattosee.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.lotuss.whattosee.data.MovieProvider
import com.lotuss.whattosee.data.model.MovieModel

class NewMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val movieProvider = MovieProvider()

    fun getNewMovies(): LiveData<List<MovieModel>> {
        return movieProvider.getNewMovies()
    }

    fun loadNewMovies() {
        movieProvider.loadNewMovies()
    }

    fun deleteFromLiked(movie: MovieModel) {
        movieProvider.deleteFromLiked(movie)
    }

    fun insertToLiked(movie: MovieModel) {
        movieProvider.insertToLiked(movie)
    }
}