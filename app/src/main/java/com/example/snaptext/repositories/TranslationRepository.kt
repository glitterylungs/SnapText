package com.example.snaptext.repositories

import com.example.snaptext.repositories.models.Translation

internal interface TranslationRepository {

    suspend fun getTranslations() : List<Translation>

    suspend fun getTranslation(id: Int): Translation

    suspend fun addTranslation(translation: Translation)
}