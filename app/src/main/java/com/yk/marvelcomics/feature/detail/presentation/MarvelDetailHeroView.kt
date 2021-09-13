package com.yk.marvelcomics.feature.detail.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yk.marvelcomics.common.load
import com.yk.marvelcomics.databinding.MarvelDetailHeroBinding

class MarvelDetailHeroView(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = MarvelDetailHeroBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        initClickListener()
    }

    private fun initClickListener() {

    }

    fun setupDataView(detailHeroDataView: DetailHeroDataView) {
        binding.textDetailHeroTitle.text = detailHeroDataView.name
        binding.textDetailHeroDate.text = detailHeroDataView.date
        binding.imageItemHero.load(detailHeroDataView.image)
    }
}

data class DetailHeroDataView(
    val image: String?,
    val name: String?,
    val description: String?,
    val date: String?
) : DetailDataView
