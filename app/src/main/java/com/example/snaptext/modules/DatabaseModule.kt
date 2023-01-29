package com.example.snaptext.modules

import androidx.room.Room
import com.example.snaptext.database.AppDatabase
import com.example.snaptext.database.dao.TranslationDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DB_NAME = "app_database"

val databaseModule = module {

    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single {
        provideTranslationDao(
            database = get()
        )
    }
}

private fun provideTranslationDao(database: AppDatabase): TranslationDao =
    database.translationDao()