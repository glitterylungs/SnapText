package com.example.snaptext.providers

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

internal class ToastProviderImpl(
    private val context: Context
) : ToastProvider {

    override fun showToast(@StringRes message: Int) =
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}