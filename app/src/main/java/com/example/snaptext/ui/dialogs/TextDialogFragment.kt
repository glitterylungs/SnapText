package com.example.snaptext.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.snaptext.R
import com.example.snaptext.databinding.FragmentTextDialogBinding

class TextDialogFragment : DialogFragment() {

    interface ActionListener {
        fun onAccept()
        fun onDismiss()
    }

    private var _binding: FragmentTextDialogBinding? = null
    private val binding get() = _binding!!

    var actionListener: ActionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppTheme_Dialog_MyDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTextDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            arguments?.getString(FIRST_LABEL_TEXT)?.let {
                firstLabelText.text = it
            } ?: run { firstLabelText.visibility = GONE }

            arguments?.getString(FIRST_TEXT)?.let {
                firstText.text = it
            } ?: run { firstText.visibility = GONE }

            arguments?.getString(SECOND_LABEL_TEXT)?.let {
                secondLabelText.text = it
            } ?: run { secondLabelText.visibility = GONE }

            arguments?.getString(SECOND_TEXT)?.let {
                secondText.text = it
            } ?: run { secondText.visibility = GONE }

            dismissButton.setOnClickListener {
                actionListener?.onDismiss()
                dismiss()
            }

            arguments?.getString(ACCEPT_BUTTON_TEXT)?.let {
                acceptButton.run {
                    text = arguments?.getString(ACCEPT_BUTTON_TEXT)
                    setOnClickListener {
                        actionListener?.onAccept()
                        dismiss()
                    }
                }
            } ?: run { acceptButton.visibility = GONE }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val FIRST_LABEL_TEXT = "first_label_text"
        private const val FIRST_TEXT = "first_text"
        private const val SECOND_LABEL_TEXT = "second_label_text"
        private const val SECOND_TEXT = "second_text"
        private const val ACCEPT_BUTTON_TEXT = "accept_text"
        const val SHOW_TRANSLATED_TEXT_DIALOG_TAG = "translated_text_dialog"
        const val SHOW_TRANSLATION_DETAILS_DIALOG_TAG = "translation_details_dialog"

        fun newInstance(
            firstLabelText: String?,
            firstText: String?,
            secondLabelText: String?,
            secondText: String?,
            acceptButtonText: String?,
            actionListener: ActionListener
        ): TextDialogFragment =
            TextDialogFragment().apply {
                arguments = bundleOf(
                    FIRST_LABEL_TEXT to firstLabelText,
                    FIRST_TEXT to firstText,
                    SECOND_LABEL_TEXT to secondLabelText,
                    SECOND_TEXT to secondText,
                    ACCEPT_BUTTON_TEXT to acceptButtonText
                )
                this.actionListener = actionListener
            }
    }
}