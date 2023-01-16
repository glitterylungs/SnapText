package com.example.snaptext.modules

import com.example.snaptext.usecases.GetTranslationUseCase
import com.example.snaptext.usecases.GetTranslationUseCaseImpl
import org.koin.dsl.module

val useCasesModule = module {

    single<GetTranslationUseCase> {
        GetTranslationUseCaseImpl(
            translationRepository = get(),
            translationRepositoryToDomainMapper = get()
        )
    }
}