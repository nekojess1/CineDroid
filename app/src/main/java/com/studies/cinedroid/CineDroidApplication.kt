package com.studies.cinedroid

import android.app.Application
import com.studies.cinedroid.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CineDroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@CineDroidApplication)
            modules(AppModule.modules)
        }
    }

}