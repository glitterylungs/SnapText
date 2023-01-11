package com.example.snaptext.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.snaptext.database.models.Translation

interface TranslationDao {

    @Query("SELECT * FROM translation WHERE id = :id")
    fun getTranslation(id: Int): Translation

    @Insert
    fun insertTranslation(translation: Translation)
}