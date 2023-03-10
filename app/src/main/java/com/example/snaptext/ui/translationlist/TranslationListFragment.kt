package com.example.snaptext.ui.translationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.snaptext.databinding.FragmentTranslationListBinding
import com.example.snaptext.providers.DialogProvider
import com.example.snaptext.ui.translationlist.adapters.TranslationListAdapter
import org.koin.android.ext.android.inject

class TranslationListFragment : Fragment() {

    private var _binding: FragmentTranslationListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TranslationListViewModel by inject()
    private val dialogProvider: DialogProvider by inject()

    private val adapter = TranslationListAdapter {
        dialogProvider.showTranslationDetails(parentFragmentManager, it)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTranslationListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.apply {
            adapter = this@TranslationListFragment.adapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        viewModel.translations.observe(viewLifecycleOwner) { translations ->
            adapter.setTranslations(translations)
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}