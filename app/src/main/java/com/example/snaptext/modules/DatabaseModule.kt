package com.example.snaptext.modules

import android.app.Application
import androidx.room.Room
import com.example.snaptext.database.AppDatabase
import com.example.snaptext.database.dao.TranslationDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val DB_NAME = "app_database"

val databaseModule = module {

    single {
        provideAppDatabase(androidApplication())
    }

    single {
        provideTranslationDao(get())
    }
}

private fun provideAppDatabase(application: Application): AppDatabase =
    Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
        .fallbackToDestructiveMigration()
        .build()

private fun provideTranslationDao(database: AppDatabase): TranslationDao =
    database.translationDao()