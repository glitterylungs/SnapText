package com.example.snaptext.ui.translationlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.snaptext.databinding.ItemTranslationListBinding
import com.example.snaptext.usecases.models.Translation

internal class TranslationListAdapter : RecyclerView.Adapter<TranslationViewHolder>() {

    private val translations = mutableListOf<Translation>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        val binding =
            ItemTranslationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TranslationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) =
        holder.bindData(translations[position])

    override fun getItemCount(): Int = translations.size

    fun setTranslations(translations: List<Translation>) {
        this.translations.clear()
        this.translations.addAll(translations)
        notifyDataSetChanged()
    }
}