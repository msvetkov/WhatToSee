package com.lotuss.whattosee.data

import android.arch.lifecycle.LiveData
import android.content.Context
import android.os.AsyncTask
import com.lotuss.whattosee.application.WhatToSeeApplication
import com.lotuss.whattosee.data.apiservice.ApiService
import com.lotuss.whattosee.data.database.MovieDao
import com.lotuss.whattosee.data.database.MovieDatabase
import com.lotuss.whattosee.data.model.MovieModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MovieProvider{

    @Inject
    lateinit var apiService: ApiService
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var movieDatabase: MovieDatabase

    var movieDao: MovieDao

    val newMovieList = mutableListOf<MovieModel>()
    private var likedMovies: LiveData<List<MovieModel>>
    private val data = MutableLiveData<List<MovieModel>>()

    init {
        WhatToSeeApplication.movieProviderComponent.inject(this)
        movieDao = movieDatabase.movieDao()
        likedMovies = movieDao.getAllLiked()
    }

    fun insertToLiked(movie: MovieModel) {
        InsertMovieAsyncTask(movieDao).execute(movie)
    }

    fun deleteFromLiked(movie: MovieModel) {
        DeleteMovieAsyncTask(movieDao).execute(movie)
    }

    fun getLikedMovies(): LiveData<List<MovieModel>> {
        return likedMovies
    }

    fun getNewMovies(): MutableLiveData<List<MovieModel>> {
        return data
    }

    fun loadNewMovies() {
        val compositeDisposable = CompositeDisposable()
        val movies = apiService.getNewMovies()

        compositeDisposable.add(movies
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                data.value = it
            }, {
                data.value = null
            })
        )
    }

    private class InsertMovieAsyncTask(val movieDao: MovieDao) : AsyncTask<MovieModel, Unit, Unit>() {

        override fun doInBackground(vararg params: MovieModel?) {
            movieDao.insert(params[0]!!)
        }
    }

    private class DeleteMovieAsyncTask(val movieDao: MovieDao) : AsyncTask<MovieModel, Unit, Unit>() {

        override fun doInBackground(vararg params: MovieModel?) {
            movieDao.delete(params[0]!!)
        }
    }
}