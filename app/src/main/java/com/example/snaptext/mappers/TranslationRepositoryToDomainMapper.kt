package com.example.snaptext.mappers

import com.example.snaptext.usecases.models.Translation
import com.example.snaptext.repositories.models.Translation as TranslationRepositoryModel

internal interface TranslationRepositoryToDomainMapper :
    Mapper<TranslationRepositoryModel, Translation>

internal class TranslationRepositoryToDomainMapperImpl : TranslationRepositoryToDomainMapper {

    override fun map(input: TranslationRepositoryModel): Translation =
        Translation(
            textBefore = input.textBefore,
            languageBefore = input.languageBefore,
            textAfter = input.textAfter,
            languageAfter = input.languageAfter
        )
}