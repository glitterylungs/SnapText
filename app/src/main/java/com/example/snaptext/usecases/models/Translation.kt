package com.example.snaptext.usecases.models

internal data class Translation(
    val id: Int,
    val textBefore: String?,
    val languageBefore: String?,
    val textAfter: String?,
    val languageAfter: String?
)