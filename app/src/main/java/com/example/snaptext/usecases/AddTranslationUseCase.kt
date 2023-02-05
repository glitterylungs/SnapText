package com.example.snaptext.usecases

import com.example.snaptext.mappers.TranslationDomainToRepositoryMapper
import com.example.snaptext.repositories.TranslationRepository
import com.example.snaptext.usecases.models.Translation

internal interface AddTranslationUseCase {

    suspend fun execute(translation: Translation)
}

internal class AddTranslationUseCaseImpl(
    private val translationRepository: TranslationRepository,
    private val translationDomainToRepositoryMapper: TranslationDomainToRepositoryMapper
) : AddTranslationUseCase {

    override suspend fun execute(translation: Translation) =
        translationRepository.addTranslation(translationDomainToRepositoryMapper.map(translation))
}