package com.example.snaptext.ui.translator

import android.graphics.Bitmap
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snaptext.R
import com.example.snaptext.providers.ToastProvider
import com.example.snaptext.textdetection.TextDetector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class TranslatorViewModel(
    private val textDetector: TextDetector,
    private val toastProvider: ToastProvider
) : ViewModel() {

    private val _textToTranslate = MutableLiveData<String>()
    val textToTranslate: LiveData<String>
        get() = _textToTranslate

    fun detectText(bitmap: Bitmap) =
        viewModelScope.launch(Dispatchers.IO) {
            textDetector.detectText(bitmap)
                .addOnSuccessListener {
                    if (it.text != "") {
                        _textToTranslate.value = it.text
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
}