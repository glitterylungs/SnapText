package com.example.snaptext.modules

import com.example.snaptext.mappers.*
import org.koin.dsl.module

val mappersModule = module {

    single<TranslationDbToRepositoryMapper> {
        TranslationDbToRepositoryMapperImpl()
    }

    single<TranslationRepositoryToDbMapper> {
        TranslationRepositoryToDbMapperImpl()
    }

    single<TranslationRepositoryToDomainMapper> {
        TranslationRepositoryToDomainMapperImpl()
    }

    single<TranslationDomainToRepositoryMapper> {
        TranslationDomainToRepositoryMapperImpl()
    }
}