package com.yk.marvelcomics.feature.detail.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yk.marvelcomics.common.load
import com.yk.marvelcomics.databinding.MarvelDetailComicBinding

class MarvelDetailComicView(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val binding = MarvelDetailComicBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        initClickListener()
    }

    private fun initClickListener() {

    }

    fun setupDataView(comicDataView: DetailComicDataView) {
        binding.textDetailComicTitle.text = comicDataView.title
        binding.textDetailComicCreators.text = comicDataView.creators?.joinToString()
        binding.textDetailComicPrice.text = "Price " + comicDataView.price
        binding.imgItemComic.load(comicDataView.image)
    }

}

data class DetailComicDataView(
    val title: String?,
    val creators: List<String>?,
    val price: String?,
    val image: String?
)