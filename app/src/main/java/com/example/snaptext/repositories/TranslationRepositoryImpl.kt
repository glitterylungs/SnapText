package com.example.snaptext.repositories

import com.example.snaptext.database.dao.TranslationDao
import com.example.snaptext.mappers.TranslationDbToRepositoryMapper
import com.example.snaptext.mappers.TranslationRepositoryToDbMapper
import com.example.snaptext.repositories.models.Translation

internal class TranslationRepositoryImpl(
    private val translationDao: TranslationDao,
    private val translationDbToRepositoryMapper: TranslationDbToRepositoryMapper,
    private val translationRepositoryToDbMapper: TranslationRepositoryToDbMapper
) : TranslationRepository {

    override suspend fun getTranslation(id: Int): Translation =
        translationDbToRepositoryMapper.map(translationDao.getTranslation(id))

    override suspend fun insertTranslation(translation: Translation): Unit =
        translationDao.insertTranslation(translationRepositoryToDbMapper.map(translation))
}