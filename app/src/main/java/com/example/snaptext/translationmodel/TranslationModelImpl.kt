package com.example.snaptext.translationmodel

import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class TranslationModelImpl:TranslationModel {
    override fun prepareTranslationModel(
        sourceLanguageCode: String,
        targetLanguageCode: String
    ): Translator {
        val options = TranslatorOptions.Builder()
            .setSourceLanguage(sourceLanguageCode)
            .setTargetLanguage(targetLanguageCode)
            .build()

        return Translation.getClient(options)
    }
}