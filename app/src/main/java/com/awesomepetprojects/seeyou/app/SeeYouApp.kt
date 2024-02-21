package com.awesomepetprojects.seeyou.app

import android.app.Application
import com.awesomepetprojects.seeyou.data.di.networking.firebaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SeeYouApp : Application() {

    override fun onCreate() {
        super.onCreate()
        configureKoin(this)
    }

    private fun configureKoin(seeYouApp: SeeYouApp) =
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(seeYouApp)
            modules(firebaseModule)
        }
}