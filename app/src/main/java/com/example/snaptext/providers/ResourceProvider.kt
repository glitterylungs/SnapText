package com.example.snaptext.providers

import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes text: Int): String
}