package com.example.snaptext.ui.translationlist.adapters

import androidx.recyclerview.widget.RecyclerView
import com.example.snaptext.databinding.ItemTranslationListBinding
import com.example.snaptext.usecases.models.Translation

internal class TranslationViewHolder(
    private val itemTranslationListBinding: ItemTranslationListBinding,
    onItemClicked: (Int) -> Unit
) : RecyclerView.ViewHolder(itemTranslationListBinding.root) {

    init {
        itemView.setOnClickListener {
            onItemClicked(adapterPosition)
        }
    }

    fun bindData(translation: Translation) {
        itemTranslationListBinding.run {
            sourceLanguage.text = translation.languageBefore
            sourceText.text = translation.textBefore
            targetLanguage.text = translation.languageAfter
            targetText.text = translation.textAfter
        }
    }
}