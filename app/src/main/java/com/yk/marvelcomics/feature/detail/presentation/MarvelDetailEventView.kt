package com.yk.marvelcomics.feature.detail.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yk.marvelcomics.common.load
import com.yk.marvelcomics.databinding.MarvelDetailEventBinding

class MarvelDetailEventView(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = MarvelDetailEventBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        initClickListener()
    }

    private fun initClickListener() {

    }

    fun setupDataView(detailEventDataView: DetailEventDataView) {
        binding.textDetailEventTitle.text = detailEventDataView.title
        binding.textDetailEventYear.text = detailEventDataView.year
        binding.imageItemEvent.load(detailEventDataView.image)
    }
}

data class DetailEventDataView(
    val title: String?,
    val description: String?,
    val year: String?,
    val image: String?
) : DetailDataView
