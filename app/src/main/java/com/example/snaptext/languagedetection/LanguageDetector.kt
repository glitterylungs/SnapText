package com.example.snaptext.languagedetection

import com.google.android.gms.tasks.Task

internal interface LanguageDetector{
    fun detectLanguage(text: String) : Task<String>
}