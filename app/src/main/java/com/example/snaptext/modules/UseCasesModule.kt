package com.example.snaptext.modules

import com.example.snaptext.usecases.*
import com.example.snaptext.usecases.GetTranslationUseCase
import com.example.snaptext.usecases.GetTranslationUseCaseImpl
import com.example.snaptext.usecases.GetTranslationsUseCase
import com.example.snaptext.usecases.GetTranslationsUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {

    single<GetTranslationUseCase> {
        GetTranslationUseCaseImpl(
            translationRepository = get(),
            translationRepositoryToDomainMapper = get()
        )
    }

    single<GetTranslationsUseCase> {
        GetTranslationsUseCaseImpl(
            translationRepository = get(),
            translationRepositoryToDomainMapper = get()
        )
    }

    single<AddTranslationUseCase> {
        AddTranslationUseCaseImpl(
            translationRepository = get(),
            translationDomainToRepositoryMapper = get()
        )
    }
}