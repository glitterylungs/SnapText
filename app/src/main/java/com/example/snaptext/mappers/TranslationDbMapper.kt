package com.example.snaptext.mappers

import com.example.snaptext.database.models.TranslationDb
import com.example.snaptext.repositories.models.Translation

internal interface TranslationDbMapper : Mapper<Translation, TranslationDb>

internal class TranslationDbMapperImpl : TranslationDbMapper {

    override fun map(input: Translation): TranslationDb =
        TranslationDb(
            textBefore = input.textBefore,
            languageBefore = input.languageBefore,
            textAfter = input.textAfter,
            languageAfter = input.languageAfter
        )
}