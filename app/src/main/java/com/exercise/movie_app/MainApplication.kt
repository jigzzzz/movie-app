package com.exercise.movie_app

import android.app.Application
import com.exercise.movie_app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication  : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule, serviceModule, netModule, repositoryModule))
        }
    }
}