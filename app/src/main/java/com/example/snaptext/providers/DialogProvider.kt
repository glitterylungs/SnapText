package com.example.snaptext.providers

import androidx.fragment.app.FragmentManager
import com.example.snaptext.usecases.models.Translation

internal interface DialogProvider {

    fun showTranslatedTextDialog(
        fragmentManager: FragmentManager,
        translatedText: String,
        onSave: () -> Unit
    )

    fun showTranslationDetails(fragmentManager: FragmentManager, translation: Translation)
}