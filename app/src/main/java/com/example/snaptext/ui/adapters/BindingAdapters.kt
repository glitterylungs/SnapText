package com.example.snaptext.ui.adapters

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.snaptext.widgets.LanguageSpinner

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("selectedLanguage")
    fun setSelectedLanguage(languageSpinner: LanguageSpinner, newSelectedLanguage: String) {
        if (languageSpinner.selectedLanguage != newSelectedLanguage) {
            println(newSelectedLanguage)
            languageSpinner.selectedLanguage = newSelectedLanguage
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "selectedLanguage")
    fun getSelectedLanguage(languageSpinner: LanguageSpinner): String =
        languageSpinner.selectedLanguage.orEmpty()

    @JvmStatic
    @BindingAdapter("selectedLanguageAttrChanged")
    fun setLanguageSpinnerListeners(
        languageSpinner: LanguageSpinner,
        attrChange: InverseBindingListener
    ) =
        languageSpinner.setOnLanguageSelected {
            attrChange.onChange()
        }
}