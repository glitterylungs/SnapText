package com.example.snaptext.ui.translator

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.snaptext.R
import com.example.snaptext.databinding.FragmentTranslatorBinding
import com.example.snaptext.ui.translator.TranslatorViewModel.Companion.codeToLanguage
import org.koin.android.ext.android.inject

class TranslatorFragment : Fragment() {

    private var _binding: FragmentTranslatorBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TranslatorViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslatorBinding.inflate(inflater, container, false)
        return binding.apply {
            viewModel = this@TranslatorFragment.viewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.languagesSpinner.languages = codeToLanguage.values.toList()

        val byteArray = arguments?.getByteArray(IMAGE_BYTE_ARRAY)
        if (byteArray != null) {
            val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
            viewModel.detectText(bitmap)
        } else {
            viewModel.showMessage(R.string.camera_data_error)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        const val IMAGE_BYTE_ARRAY = "imageByteArray"
    }
}