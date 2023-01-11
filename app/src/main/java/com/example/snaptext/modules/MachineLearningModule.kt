package com.example.snaptext.modules

import com.example.snaptext.languagedetection.LanguageDetector
import com.example.snaptext.languagedetection.LanguageDetectorImpl
import com.example.snaptext.textdetection.TextDetector
import com.example.snaptext.textdetection.TextDetectorImpl
import org.koin.dsl.module

val machineLearningModule = module {

    factory<TextDetector> {
        TextDetectorImpl()
    }

    factory<LanguageDetector> {
        LanguageDetectorImpl()
    }
}