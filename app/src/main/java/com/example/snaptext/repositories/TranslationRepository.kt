package com.example.snaptext.repositories

import com.example.snaptext.repositories.models.Translation

internal interface TranslationRepository {

    suspend fun getTranslation(id: Int): Translation

    suspend fun insertTranslation(translation: Translation)
}