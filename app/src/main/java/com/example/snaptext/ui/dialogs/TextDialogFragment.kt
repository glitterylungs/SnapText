package com.example.snaptext.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import com.example.snaptext.databinding.FragmentTextDialogBinding

class TextDialogFragment : DialogFragment() {

    interface ActionListener {
        fun onAccept()
        fun onDismiss()
    }

    private var _binding: FragmentTextDialogBinding? = null
    private val binding get() = _binding!!

    var actionListener: ActionListener? = null

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
            dialogText.text = arguments?.getString(TEXT)
            dismissButton.setOnClickListener {
                actionListener?.onDismiss()
                dismiss()
            }
            acceptButton.run {
                text = arguments?.getString(ACCEPT_BUTTON_TEXT)
                setOnClickListener {
                    actionListener?.onAccept()
                    dismiss()
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val TEXT = "text"
        private const val ACCEPT_BUTTON_TEXT = "accept_text"
        const val SHOW_TEXT_DIALOG_TAG = "text_dialog"

        fun newInstance(
            text: String,
            acceptButtonText: String,
            actionListener: ActionListener
        ): TextDialogFragment =
            TextDialogFragment().apply {
                arguments = bundleOf(
                    TEXT to text,
                    ACCEPT_BUTTON_TEXT to acceptButtonText
                )
                this.actionListener = actionListener
            }
    }
}