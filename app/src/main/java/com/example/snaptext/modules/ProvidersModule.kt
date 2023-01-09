package com.example.snaptext.modules

import com.example.snaptext.providers.ToastProvider
import com.example.snaptext.providers.ToastProviderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val providersModule = module {

    single<ToastProvider> {
        ToastProviderImpl(androidContext())
    }
}