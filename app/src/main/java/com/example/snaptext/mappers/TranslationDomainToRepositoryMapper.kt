package com.example.snaptext.mappers

import com.example.snaptext.usecases.models.Translation as TranslationDomainModel
import com.example.snaptext.repositories.models.Translation

internal interface TranslationDomainToRepositoryMapper : Mapper<TranslationDomainModel, Translation>

internal class TranslationDomainToRepositoryMapperImpl : TranslationDomainToRepositoryMapper {

    override fun map(input: TranslationDomainModel): Translation =
        Translation(
            textBefore = input.textBefore,
            languageBefore = input.languageBefore,
            textAfter = input.textAfter,
            languageAfter = input.languageAfter
        )
}