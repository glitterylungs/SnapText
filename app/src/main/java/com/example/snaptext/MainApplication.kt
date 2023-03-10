package com.example.snaptext

import android.app.Application
import com.example.snaptext.modules.databaseModule
import com.example.snaptext.modules.machineLearningModule
import com.example.snaptext.modules.mappersModule
import com.example.snaptext.modules.providersModule
import com.example.snaptext.modules.repositoriesModule
import com.example.snaptext.modules.translationModule
import com.example.snaptext.modules.useCasesModule
import com.example.snaptext.modules.viewModelsModule
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
                useCasesModule,
                translationModule
            )
        }
    }
}