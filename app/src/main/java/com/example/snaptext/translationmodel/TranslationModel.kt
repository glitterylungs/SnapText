package com.example.snaptext.translationmodel

import com.google.mlkit.nl.translate.Translator

interface TranslationModel {
    fun prepareTranslationModel(sourceLanguageCode: String, targetLanguageCode: String): Translator
}