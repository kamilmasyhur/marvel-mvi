package com.yk.marvelcomics.feature.detail.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.yk.marvelcomics.R
import com.yk.marvelcomics.databinding.MarvelDetailSynopsisBinding

class MarvelDetailSynopsisView(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = MarvelDetailSynopsisBinding.inflate(LayoutInflater.from(context), this, true)

    fun setupDataView(detailSynopsisDataView: DetailSynopsisDataView?) {
        if (detailSynopsisDataView?.synopsis.isNullOrEmpty()) {
            binding.textDetailSynopsis.text = context.getString(R.string.marvel_synopsis_placeholder)
        } else {
            binding.textDetailSynopsis.text = detailSynopsisDataView?.synopsis
        }
    }
}

data class DetailSynopsisDataView(
    val synopsis: String?
) : DetailDataView
