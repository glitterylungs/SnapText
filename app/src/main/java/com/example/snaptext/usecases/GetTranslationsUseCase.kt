package com.example.snaptext.usecases

import com.example.snaptext.mappers.TranslationRepositoryToDomainMapper
import com.example.snaptext.repositories.TranslationRepository
import com.example.snaptext.usecases.models.Translation

internal interface GetTranslationsUseCase {

    suspend fun getTranslations() : List<Translation>
}

internal class GetTranslationsUseCaseImpl(
    private val translationRepository: TranslationRepository,
    private val translationRepositoryToDomainMapper: TranslationRepositoryToDomainMapper
) : GetTranslationsUseCase {

    override suspend fun getTranslations(): List<Translation> =
        translationRepository.getTranslations().map {
            translationRepositoryToDomainMapper.map(it)
        }
}