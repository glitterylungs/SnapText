package com.example.snaptext.modules

import com.example.snaptext.translationmodel.TranslationModel
import com.example.snaptext.translationmodel.TranslationModelImpl
import org.koin.dsl.module

val translationModule = module {
    factory<TranslationModel> {
        TranslationModelImpl()
    }
}