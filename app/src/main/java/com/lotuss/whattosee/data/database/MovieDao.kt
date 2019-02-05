package com.lotuss.whattosee.data.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.lotuss.whattosee.data.model.MovieModel

@Dao
interface MovieDao{

    @Insert
    fun insert(movie: MovieModel)

    @Delete
    fun delete(movie: MovieModel)

    @Query("SELECT * FROM liked_movies")
    fun getAllLiked():LiveData<List<MovieModel>>
}