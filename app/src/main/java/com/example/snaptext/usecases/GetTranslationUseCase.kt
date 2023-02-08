package com.example.snaptext.usecases

import com.example.snaptext.mappers.TranslationRepositoryToDomainMapper
import com.example.snaptext.repositories.TranslationRepository
import com.example.snaptext.usecases.models.Translation

internal interface GetTranslationUseCase {

    suspend fun execute(id: Int): Translation
}

internal class GetTranslationUseCaseImpl(
    private val translationRepository: TranslationRepository,
    private val translationRepositoryToDomainMapper: TranslationRepositoryToDomainMapper
) : GetTranslationUseCase {

    override suspend fun execute(id: Int): Translation =
        translationRepositoryToDomainMapper.map(translationRepository.getTranslation(id))
}