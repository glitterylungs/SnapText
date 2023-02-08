package com.example.snaptext.providers

import androidx.annotation.StringRes

internal interface ToastProvider {

    fun showToast(@StringRes message: Int)
}