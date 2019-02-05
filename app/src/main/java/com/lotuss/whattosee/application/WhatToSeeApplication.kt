package com.lotuss.whattosee.application

import android.app.Application
import com.lotuss.whattosee.di.*

class WhatToSeeApplication: Application() {

    init {
        movieProviderComponent = DaggerMovieProviderComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule())
            .build()
    }
    companion object {
        lateinit var movieProviderComponent: MovieProviderComponent
    }

    override fun onCreate() {
        super.onCreate()

        movieProviderComponent = DaggerMovieProviderComponent.builder()
            .applicationModule(ApplicationModule(this))
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule())
            .build()
    }
}