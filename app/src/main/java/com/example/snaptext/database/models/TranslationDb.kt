package com.example.snaptext.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translation")
internal data class TranslationDb(
    @ColumnInfo(name = "textBeforeTranslation") val textBefore: String?,
    @ColumnInfo(name = "languageBeforeTranslation") val languageBefore: String?,
    @ColumnInfo(name = "textAfterTranslation") val textAfter: String?,
    @ColumnInfo(name = "languageAfterTranslation") val languageAfter: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}