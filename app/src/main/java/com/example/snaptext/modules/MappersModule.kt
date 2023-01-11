package com.example.snaptext.modules

import com.example.snaptext.mappers.TranslationDbMapper
import com.example.snaptext.mappers.TranslationDbMapperImpl
import com.example.snaptext.mappers.TranslationMapper
import com.example.snaptext.mappers.TranslationMapperImpl
import org.koin.dsl.module

val mappersModule = module {

    single<TranslationMapper> {
        TranslationMapperImpl()
    }

    single<TranslationDbMapper> {
        TranslationDbMapperImpl()
    }
}