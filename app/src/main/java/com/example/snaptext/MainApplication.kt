package com.example.snaptext

import android.app.Application
import com.example.snaptext.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                machineLearningModule,
                viewModelsModule,
                providersModule,
                databaseModule,
                repositoriesModule,
                mappersModule,
                useCasesModule
            )
        }
    }
}