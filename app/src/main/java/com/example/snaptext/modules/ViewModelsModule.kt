package com.example.snaptext.modules

import com.example.snaptext.ui.MainActivityViewModel
import com.example.snaptext.ui.start.StartViewModel
import com.example.snaptext.ui.translator.TranslatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel {
        MainActivityViewModel(
            toastProvider = get()
        )
    }

    viewModel {
        StartViewModel()
    }

    viewModel {
        TranslatorViewModel(
            textDetector = get(),
            toastProvider = get()
        )
    }
}