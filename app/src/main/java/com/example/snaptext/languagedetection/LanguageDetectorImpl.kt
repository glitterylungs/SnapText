package com.example.snaptext.languagedetection

import com.google.android.gms.tasks.Task
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentifier

internal class LanguageDetectorImpl : LanguageDetector {
    override fun detectLanguage(text: String): Task<String> {
        val languageIdentifier: LanguageIdentifier = LanguageIdentification.getClient()
        return languageIdentifier.identifyLanguage(text)
    }
}