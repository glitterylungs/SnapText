package com.example.snaptext.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.snaptext.database.dao.TranslationDao
import com.example.snaptext.database.models.TranslationDb

@Database(entities = [TranslationDb::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {

    abstract fun translationDao(): TranslationDao
}