package com.example.snaptext.ui.translationlist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.snaptext.databinding.ItemTranslationListBinding
import com.example.snaptext.usecases.models.Translation

internal class TranslationListAdapter(
    private val onItemClicked: (Translation) -> Unit
) : RecyclerView.Adapter<TranslationViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Translation>() {
        override fun areContentsTheSame(oldItem: Translation, newItem: Translation): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: Translation, newItem: Translation): Boolean =
            oldItem.id == newItem.id
    }

    private val translations = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TranslationViewHolder {
        val binding =
            ItemTranslationListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TranslationViewHolder(binding) { onItemClicked(translations.currentList[it]) }
    }

    override fun onBindViewHolder(holder: TranslationViewHolder, position: Int) =
        holder.bindData(translations.currentList[position])

    override fun getItemCount(): Int = translations.currentList.size

    fun setTranslations(translations: List<Translation>) {
        this.translations.submitList(translations)
    }
}