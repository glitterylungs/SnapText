package com.example.snaptext.providers

import androidx.fragment.app.FragmentManager
import com.example.snaptext.R
import com.example.snaptext.ui.dialogs.TextDialogFragment
import com.example.snaptext.ui.dialogs.TextDialogFragment.Companion.SHOW_TEXT_DIALOG_TAG

class DialogProviderImpl(
    private val resourceProvider: ResourceProvider
) : DialogProvider {

    override fun showTranslatedTextDialog(
        fragmentManager: FragmentManager,
        translatedText: String,
        onSave: () -> Unit
    ) =
        TextDialogFragment.newInstance(
            text = translatedText,
            acceptButtonText = resourceProvider.getString(R.string.save),
            actionListener = object : TextDialogFragment.ActionListener {
                override fun onAccept() {
                    onSave()
                }

                override fun onDismiss() = Unit
            }
        ).show(fragmentManager, SHOW_TEXT_DIALOG_TAG)
}