package com.example.snaptext.ui.translator

import android.graphics.Bitmap
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snaptext.R
import com.example.snaptext.languagedetection.LanguageDetector
import com.example.snaptext.providers.ToastProvider
import com.example.snaptext.textdetection.TextDetector
import com.example.snaptext.translationmodel.TranslationModel
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class TranslatorViewModel(
    private val textDetector: TextDetector,
    private val toastProvider: ToastProvider,
    private val languageDetector: LanguageDetector,
    private val translationModel: TranslationModel
) : ViewModel() {

    private val _textToTranslate = MutableLiveData<String>()
    val textToTranslate: LiveData<String>
        get() = _textToTranslate

    private val _translatedText = MutableLiveData<String>()
    val translatedText: LiveData<String>
        get() = _translatedText

    private val _sourceLanguage = MutableLiveData<String>()
    val sourceLanguage: LiveData<String>
        get() = _sourceLanguage

    lateinit var sourceLanguageCode: String
    lateinit var targetLanguageCode: String
    lateinit var translator: Translator

    fun detectText(bitmap: Bitmap) =
        viewModelScope.launch(Dispatchers.IO) {
            detectLanguage()
            textDetector.detectText(bitmap)
                .addOnSuccessListener {
                    if (it.text != "") {
                        _textToTranslate.value = it.text
                        detectLanguage() // tymczasowo tu
                    } else {
                        showMessage(R.string.no_text_to_detect)
                    }
                }
                .addOnFailureListener {
                    showMessage(R.string.detect_text_error)
                }

        }

    fun showMessage(@StringRes message: Int) =
        toastProvider.showToast(message)

    private fun detectLanguage() {
        viewModelScope.launch(Dispatchers.IO) {
            languageDetector.detectLanguage(_textToTranslate.value!!)
                .addOnSuccessListener { languageCode ->
                    if (languageCode == "und") {
                        _sourceLanguage.value = "Couldn't detect language"
                    } else {
                        _sourceLanguage.value = languageCodeToLanguage(languageCode)
                        sourceLanguageCode = languageCode
                    }
                }
        }
    }

    fun translate() {
        viewModelScope.launch(Dispatchers.IO) {
            translator = translationModel.prepareTranslationModel(
                sourceLanguageCode,
                TranslateLanguage.POLISH // <------
            )
            translator.downloadModelIfNeeded().addOnSuccessListener {
                translator.translate(_textToTranslate.value!!).addOnSuccessListener {
                    _translatedText.value = it
                }
            }
        }
    }

    private fun languageCodeToLanguage(languageCode: String): String {
        return when (languageCode) {
            "en" -> "English"
            "af" -> "Afrikaans"
            "sq" -> "Albanian"
            "ar" -> "Arabic"
            "be" -> "Belarusian"
            "bg" -> "Bulgarian"
            "bn" -> "Bengali"
            "ca" -> "Catalan"
            "zh" -> "Chinese"
            "hr" -> "Croatian"
            "cs" -> "Czech"
            "da" -> "Danish"
            "nl" -> "Dutch"
            "eo" -> "Esperanto"
            "et" -> "Estonian"
            "fi" -> "Finnish"
            "fr" -> "French"
            "gl" -> "Galician"
            "ka" -> "Georgian"
            "de" -> "German"
            "el" -> "Greek"
            "gu" -> "Gujarati"
            "ht" -> "Hitian Creole"
            "he" -> "Hebrew"
            "hi" -> "Hindi"
            "hu" -> "Hungarian"
            "is" -> "Icelandic"
            "id" -> "Indonesian"
            "ga" -> "Irish"
            "it" -> "Italian"
            "ja" -> "Japanese"
            "kn" -> "Kannada"
            "ko" -> "Korean"
            "lt" -> "Lithuanian"
            "lv" -> "Latvian"
            "mk" -> "Macedonian"
            "mr" -> "Marathi"
            "ms" -> "Malay"
            "mt" -> "Maltese"
            "no" -> "Norwegian"
            "fa" -> "Persian"
            "pl" -> "Polish"
            "pt" -> "Portuguese"
            "ro" -> "Romanian"
            "ru" -> "Russian"
            "sk" -> "Slovak"
            "sl" -> "Slovenian"
            "es" -> "Spanish"
            "sv" -> "Swedish"
            "sw" -> "Swahili"
            "tl" -> "Tagalog"
            "ta" -> "Tamil"
            "te" -> "Telugu"
            "th" -> "Thai"
            "tr" -> "Turkish"
            "uk" -> "Ukrainian"
            "ur" -> "Urdu"
            "vi" -> "Vietnamese"
            "cy" -> "Welsh"
            else -> "Couldn't detect language"
        }
    }
}