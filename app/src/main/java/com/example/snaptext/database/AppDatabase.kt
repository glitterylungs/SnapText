package com.example.snaptext.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snaptext.database.dao.TranslationDao
import com.example.snaptext.database.models.Translation

@Database(entities = [Translation::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun translationDao(): TranslationDao
}