package com.example.snaptext.ui.translator.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.snaptext.R
import com.example.snaptext.databinding.ItemSpinnerBinding

class LanguageArrayAdapter(
    context: Context,
    languages: List<String>
) : ArrayAdapter<String>(context, 0, languages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View =
        initView(position, convertView, parent)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View =
        initView(position, convertView, parent)

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        val language = getItem(position)
        val newView = convertView ?: ItemSpinnerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).root

        newView.findViewById<TextView>(R.id.languageTextView).text = language

        return newView
    }
}