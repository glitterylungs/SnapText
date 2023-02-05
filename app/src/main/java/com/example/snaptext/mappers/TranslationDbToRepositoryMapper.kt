package com.example.snaptext.mappers

import com.example.snaptext.database.models.TranslationDb
import com.example.snaptext.repositories.models.Translation

internal interface TranslationDbToRepositoryMapper : Mapper<TranslationDb, Translation>

internal class TranslationDbToRepositoryMapperImpl : TranslationDbToRepositoryMapper {

    override fun map(input: TranslationDb): Translation =
        Translation(
            id = input.id,
            textBefore = input.textBefore,
            languageBefore = input.languageBefore,
            textAfter = input.textAfter,
            languageAfter = input.languageAfter
        )
}