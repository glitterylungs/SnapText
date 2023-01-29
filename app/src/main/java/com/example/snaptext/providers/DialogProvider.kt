package com.example.snaptext.providers

import androidx.fragment.app.FragmentManager

interface DialogProvider {

    fun showTranslatedTextDialog(
        fragmentManager: FragmentManager,
        translatedText: String,
        onSave: () -> Unit
    )
}