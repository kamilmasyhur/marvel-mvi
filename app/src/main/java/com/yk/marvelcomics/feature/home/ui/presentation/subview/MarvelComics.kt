package com.yk.marvelcomics.feature.home.ui.presentation.subview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yk.marvelcomics.databinding.MarvelComicsViewBinding
import com.yk.marvelcomics.feature.detail.presentation.DetailDataView
import com.yk.marvelcomics.feature.home.ui.adapter.ComicsAdapter

class MarvelComics @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = MarvelComicsViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val adapter = ComicsAdapter()

    init {
        binding.rvComics.apply {
            adapter = this@MarvelComics.adapter
        }
    }

    fun addComicsItem(comicDataView: ComicsDataView, listener: ComicListener? = null) {
        adapter.initItems(comicDataView.comics, listener)
        binding.tvSeeAll.setOnClickListener {
            listener?.onSeeAllComicClick()
        }
    }
}

data class ComicsDataView(
    val comics: List<Comic>
): DetailDataView {
    data class Comic(
        val title: String?,
        val creator: String?,
        val thumbnail: String?,
        val id: Int?
    )
}

interface ComicListener {
    fun onComicClick(result: ComicsDataView.Comic)
    fun onSeeAllComicClick()
}
