package com.example.snaptext.providers

import android.content.Context

class ResourceProviderImpl(
    val context: Context
) : ResourceProvider {

    override fun getString(text: Int) = context.getString(text)
}