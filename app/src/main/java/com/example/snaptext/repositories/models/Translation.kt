package com.example.snaptext.repositories.models

internal data class Translation(
    val textBefore: String?,
    val languageBefore: String?,
    val textAfter: String?,
    val languageAfter: String?
)