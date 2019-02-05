package com.lotuss.whattosee.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.lotuss.whattosee.data.MovieProvider
import com.lotuss.whattosee.data.model.MovieModel

class LikedMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val movieProvider = MovieProvider()

    fun deleteFromLiked(movie: MovieModel){
        movieProvider.deleteFromLiked(movie)
    }

    fun getLikedMovies(): LiveData<List<MovieModel>> {
        return movieProvider.getLikedMovies()
    }
}
