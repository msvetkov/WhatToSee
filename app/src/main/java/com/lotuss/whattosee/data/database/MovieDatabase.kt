package com.lotuss.whattosee.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.lotuss.whattosee.data.model.MovieModel


@Database(entities = [MovieModel::class], version = 2, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao(): MovieDao
}