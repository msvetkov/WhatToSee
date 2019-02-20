package com.lotuss.whattosee.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.lotuss.whattosee.data.model.MovieModel

@Dao
interface MovieDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieModel)

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getById(movieId: Long): LiveData<MovieModel>

    @Query("UPDATE movies SET isLiked = :movieIsLiked WHERE id = :movieId")
    fun addToLiked(movieId: Long, movieIsLiked: Boolean)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieModel>>
}