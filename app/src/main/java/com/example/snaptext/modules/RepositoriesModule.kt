package com.example.snaptext.modules

import com.example.snaptext.repositories.TranslationRepository
import com.example.snaptext.repositories.TranslationRepositoryImpl
import org.koin.dsl.module

val repositoriesModule = module {

    single<TranslationRepository> {
        TranslationRepositoryImpl(
            translationDao = get(),
            translationDbToRepositoryMapper = get(),
            translationRepositoryToDbMapper = get()
        )
    }
}