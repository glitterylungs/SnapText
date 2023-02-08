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
import com.example.snaptext.usecases.AddTranslationUseCase
import com.example.snaptext.usecases.models.Translation
import com.google.mlkit.nl.translate.Translator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class TranslatorViewModel(
    private val addTranslationUseCase: AddTranslationUseCase,
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

    val targetLanguage = MutableLiveData<String>(codeToLanguage["pl"])

    private var sourceLanguageCode: String? = null
    private val targetLanguageCode = MutableLiveData<String>(codeToLanguage.entries.find {
        it.value == targetLanguage.value
    }?.key)
    private var translator: Translator? = null

    fun detectText(bitmap: Bitmap) =
        viewModelScope.launch(Dispatchers.IO) {
            textDetector.detectText(bitmap)
                .addOnSuccessListener {
                    if (it.text != "") {
                        _textToTranslate.value = it.text
                        detectLanguage()
                    } else {
                        showMessage(R.string.no_text_to_detect)
                    }
                }
                .addOnFailureListener {
                    showMessage(R.string.detect_text_error)
                }
        }

    private fun detectLanguage() =
        viewModelScope.launch(Dispatchers.IO) {
            languageDetector.detectLanguage(_textToTranslate.value!!)
                .addOnSuccessListener { languageCode ->
                    if (languageCode == "und" || languageCode == "el-Latn") {
                        _sourceLanguage.value = "NaN"
                        showMessage(R.string.language_not_recognized)
                    } else {
                        _sourceLanguage.value = codeToLanguage[languageCode]
                        sourceLanguageCode = languageCode
                    }
                }
                .addOnFailureListener {
                    showMessage(R.string.detect_language_error)
                }
        }

    fun translate() {
        if (_textToTranslate.value != "" && _sourceLanguage.value != "") {
            viewModelScope.launch(Dispatchers.IO) {
                translator = targetLanguageCode.value?.let { targetCode ->
                    sourceLanguageCode?.let { sourceCode ->
                        translationModel.prepareTranslationModel(sourceCode, targetCode)
                    }
                }
                translator?.downloadModelIfNeeded()?.addOnSuccessListener {
                    translator?.translate(_textToTranslate.value!!)?.addOnSuccessListener {
                        _translatedText.value = it
                    }
                }
            }
        } else {
            showMessage(R.string.try_again)
        }
    }

    fun addTranslation() {
        val translation = Translation(
            id = DEFAULT_ID,
            textBefore = textToTranslate.value,
            languageBefore = sourceLanguage.value,
            textAfter = translatedText.value,
            languageAfter = targetLanguage.value
        )
        viewModelScope.launch(Dispatchers.IO) {
            addTranslationUseCase.execute(translation)
        }
    }

    fun showMessage(@StringRes message: Int) =
        toastProvider.showToast(message)

    companion object {

        private const val DEFAULT_ID = 0

        val codeToLanguage = mapOf(
            "en" to "English",
            "af" to "Afrikaans",
            "sq" to "Albanian",
            "ar" to "Arabic",
            "be" to "Belarusian",
            "bg" to "Bulgarian",
            "bn" to "Bengali",
            "ca" to "Catalan",
            "zh" to "Chinese",
            "hr" to "Croatian",
            "cs" to "Czech",
            "da" to "Danish",
            "nl" to "Dutch",
            "eo" to "Esperanto",
            "et" to "Estonian",
            "fi" to "Finnish",
            "fr" to "French",
            "gl" to "Galician",
            "ka" to "Georgian",
            "de" to "German",
            "el" to "Greek",
            "gu" to "Gujarati",
            "ht" to "Hitian Creole",
            "he" to "Hebrew",
            "hi" to "Hindi",
            "hu" to "Hungarian",
            "is" to "Icelandic",
            "id" to "Indonesian",
            "ga" to "Irish",
            "it" to "Italian",
            "ja" to "Japanese",
            "kn" to "Kannada",
            "ko" to "Korean",
            "lt" to "Lithuanian",
            "lv" to "Latvian",
            "mk" to "Macedonian",
            "mr" to "Marathi",
            "ms" to "Malay",
            "mt" to "Maltese",
            "no" to "Norwegian",
            "fa" to "Persian",
            "pl" to "Polish",
            "pt" to "Portuguese",
            "ro" to "Romanian",
            "ru" to "Russian",
            "sk" to "Slovak",
            "sl" to "Slovenian",
            "es" to "Spanish",
            "sv" to "Swedish",
            "sw" to "Swahili",
            "tl" to "Tagalog",
            "ta" to "Tamil",
            "te" to "Telugu",
            "th" to "Thai",
            "tr" to "Turkish",
            "uk" to "Ukrainian",
            "ur" to "Urdu",
            "vi" to "Vietnamese",
            "cy" to "Welsh"
        )
    }
}