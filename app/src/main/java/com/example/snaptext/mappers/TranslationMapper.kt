package com.example.snaptext.mappers

import com.example.snaptext.database.models.TranslationDb
import com.example.snaptext.repositories.models.Translation

internal interface TranslationMapper : Mapper<TranslationDb, Translation>

internal class TranslationMapperImpl : TranslationMapper {

    override fun map(input: TranslationDb): Translation =
        Translation(
            textBefore = input.textBefore,
            languageBefore = input.languageBefore,
            textAfter = input.textAfter,
            languageAfter = input.languageAfter
        )
}