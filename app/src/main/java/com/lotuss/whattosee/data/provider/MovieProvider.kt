package com.lotuss.whattosee.data.provider

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.AsyncTask
import com.lotuss.whattosee.application.WhatToSeeApplication
import com.lotuss.whattosee.data.apiservice.ApiService
import com.lotuss.whattosee.data.database.MovieDao
import com.lotuss.whattosee.data.database.MovieDatabase
import com.lotuss.whattosee.data.model.MovieModel
import io.reactivex.Observable
import javax.inject.Inject
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

    private var movieDao: MovieDao

    private var newMovieList: LiveData<List<MovieModel>>
    val isConnect: MutableLiveData<Boolean> = MutableLiveData()

    init {
        WhatToSeeApplication.movieProviderComponent.inject(this)
        movieDao = movieDatabase.movieDao()
        newMovieList = movieDao.getAllMovies()
    }

    fun addToLiked(movie: MovieModel) {
        AddToLikedAsyncTask(movieDao).execute(movie)
    }

    fun getById(movieID: Long): LiveData<MovieModel> {
        return movieDao.getById(movieID)
    }

    fun getMovies(): LiveData<List<MovieModel>> {
        newMovieList.observeForever {
            if (it!!.isEmpty())
                loadNewMovies()
        }
        return newMovieList
    }

    private fun loadNewMovies() {
        val compositeDisposable = CompositeDisposable()
        val movies = apiService.getNewMovies()
        compositeDisposable.add(movies
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                insertMovie(it)
                isConnect.value = true
            }, {
                isConnect.value = false
            })
        )
    }

    private fun insertMovie(movies: List<MovieModel>){
        val bufferItems = mutableListOf<MovieModel>()

        var id = 1
        movies.forEach {movie ->
            movie.id = id++
            bufferItems.add(movie)
        }

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(Observable.fromCallable {
            bufferItems.forEach {
                try {
                    movieDao.insertMovie(it)
                }catch (e: SQLiteConstraintException){
                    //Some replies from server come in wrong format.
                }
            }
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
        )
    }


    private class AddToLikedAsyncTask(val movieDao: MovieDao) : AsyncTask<MovieModel, Unit, Unit>() {

        override fun doInBackground(vararg params: MovieModel?) {
            movieDao.addToLiked(params[0]!!.id.toLong(), params[0]!!.isLiked)
        }
    }
}