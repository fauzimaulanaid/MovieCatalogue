package com.fauzimaulana.moviecatalogue

import android.app.Application
import com.fauzimaulana.moviecatalogue.core.di.databaseModule
import com.fauzimaulana.moviecatalogue.core.di.networkModule
import com.fauzimaulana.moviecatalogue.core.di.repositoryModule
import com.fauzimaulana.moviecatalogue.di.useCaseModule
import com.fauzimaulana.moviecatalogue.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}