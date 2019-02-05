package com.lotuss.whattosee.di

import com.lotuss.whattosee.data.MovieProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class, NetworkModule::class, ApplicationModule::class])
interface MovieProviderComponent{
    fun inject(movieProvider: MovieProvider)
}