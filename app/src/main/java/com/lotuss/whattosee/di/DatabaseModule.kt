package com.lotuss.whattosee.di

import android.arch.persistence.room.Room
import android.content.Context
import com.lotuss.whattosee.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule{

    @Provides
    @Singleton
    fun getDatabase(context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDatabase::class.java, "movies_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}