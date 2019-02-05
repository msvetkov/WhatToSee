package com.lotuss.whattosee.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(var context: Context) {

    @Provides
    fun getContextDagger(): Context {
        return context
    }
}