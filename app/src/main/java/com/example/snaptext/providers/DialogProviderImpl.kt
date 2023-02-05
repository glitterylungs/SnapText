package com.example.snaptext.providers

import androidx.fragment.app.FragmentManager
import com.example.snaptext.R
import com.example.snaptext.ui.dialogs.TextDialogFragment
import com.example.snaptext.ui.dialogs.TextDialogFragment.Companion.SHOW_TRANSLATED_TEXT_DIALOG_TAG
import com.example.snaptext.ui.dialogs.TextDialogFragment.Companion.SHOW_TRANSLATION_DETAILS_DIALOG_TAG
import com.example.snaptext.usecases.models.Translation

internal class DialogProviderImpl(
    private val resourceProvider: ResourceProvider
) : DialogProvider {

    override fun showTranslatedTextDialog(
        fragmentManager: FragmentManager,
        translatedText: String,
        onSave: () -> Unit
    ) =
        TextDialogFragment.newInstance(
            firstLabelText = null,
            firstText = translatedText,
            secondLabelText = null,
            secondText = null,
            acceptButtonText = resourceProvider.getString(R.string.save),
            actionListener = object : TextDialogFragment.ActionListener {
                override fun onAccept() {
                    onSave()
                }

                override fun onDismiss() = Unit
            }
        ).show(fragmentManager, SHOW_TRANSLATED_TEXT_DIALOG_TAG)

    override fun showTranslationDetails(
        fragmentManager: FragmentManager,
        translation: Translation
    ) =
        TextDialogFragment.newInstance(
            firstLabelText = translation.languageBefore,
            firstText = translation.textBefore,
            secondLabelText = translation.languageAfter,
            secondText = translation.textAfter,
            acceptButtonText = null,
            actionListener = object : TextDialogFragment.ActionListener {
                override fun onAccept() = Unit
                override fun onDismiss() = Unit
            }
        ).show(fragmentManager, SHOW_TRANSLATION_DETAILS_DIALOG_TAG)
}