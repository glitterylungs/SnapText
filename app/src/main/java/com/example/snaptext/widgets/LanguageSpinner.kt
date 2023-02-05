package com.example.snaptext.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import com.example.snaptext.ui.translator.adapters.LanguageArrayAdapter

class LanguageSpinner @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : AppCompatSpinner(context, attributeSet) {

    init {
        onItemSelectedListener = object : OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val language = languages.getOrNull(position) ?: return
                selectedLanguage = language
                languageSelectedListener(language)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) = Unit
        }
    }

    private var languageSelectedListener: (String) -> Unit = {}

    var languages: List<String> = emptyList()
        set(value) {
            field = value
            adapter = LanguageArrayAdapter(context, value)
            selectedLanguage?.let {
                setLanguage(selectedLanguage)
            }
        }

    var selectedLanguage: String? = null
        set(value) {
            field = value
            setLanguage(value)
        }

    fun setOnLanguageSelected(listener: (String) -> Unit) {
        languageSelectedListener = listener
    }

    private fun setLanguage(language: String?) {
        val position = languages.indexOfFirst { it == language }
        if (selectedItemPosition != position && position != INVALID_INDEX) {
            setSelection(position)
        }
    }

    companion object {
        private const val INVALID_INDEX = -1
    }
}