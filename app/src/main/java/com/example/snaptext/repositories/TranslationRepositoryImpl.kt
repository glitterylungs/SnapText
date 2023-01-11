package com.example.snaptext.repositories

import com.example.snaptext.database.dao.TranslationDao
import com.example.snaptext.mappers.TranslationDbMapper
import com.example.snaptext.mappers.TranslationMapper
import com.example.snaptext.repositories.models.Translation

internal class TranslationRepositoryImpl(
    private val translationDao: TranslationDao,
    private val translationMapper: TranslationMapper,
    private val translationDbMapper: TranslationDbMapper
) : TranslationRepository {

    override suspend fun getTranslation(id: Int): Translation =
        translationMapper.map(translationDao.getTranslation(id))

    override suspend fun insertTranslation(translation: Translation): Unit =
        translationDao.insertTranslation(translationDbMapper.map(translation))
}