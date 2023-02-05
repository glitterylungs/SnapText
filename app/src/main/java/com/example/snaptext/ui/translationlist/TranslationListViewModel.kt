package com.example.snaptext.ui.translationlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.snaptext.usecases.GetTranslationsUseCase
import com.example.snaptext.usecases.models.Translation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class TranslationListViewModel(
    private val getTranslationsUseCase: GetTranslationsUseCase
) : ViewModel() {

    init {
        getTranslations()
    }

    private val _translations = MutableLiveData<List<Translation>>()
    val translations: LiveData<List<Translation>>
        get() = _translations

    private fun getTranslations() {
        viewModelScope.launch(Dispatchers.IO) {
            _translations.postValue(getTranslationsUseCase.execute())
        }
    }
}