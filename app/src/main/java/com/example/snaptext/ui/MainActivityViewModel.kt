package com.example.snaptext.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import com.example.snaptext.providers.ToastProvider

internal class MainActivityViewModel(
    private val toastProvider: ToastProvider
) : ViewModel() {

    fun showMessage(@StringRes message: Int) {
        toastProvider.showToast(message)
    }
}