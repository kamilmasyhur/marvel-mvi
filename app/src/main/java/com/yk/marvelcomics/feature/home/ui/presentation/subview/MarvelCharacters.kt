package com.yk.marvelcomics.feature.home.ui.presentation.subview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yk.marvelcomics.databinding.MarvelCharactersViewBinding
import com.yk.marvelcomics.feature.home.ui.adapter.CharactersAdapter

class MarvelCharacters @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = MarvelCharactersViewBinding.inflate(LayoutInflater.from(context), this, true)
    private val adapter = CharactersAdapter()

    init {
        binding.rvComics.apply {
            adapter = this@MarvelCharacters.adapter
        }
    }

    fun addCharactersItem(comicDataView: CharactersDataView) =
        adapter.initItems(comicDataView.characters)
}

data class CharactersDataView(
    val characters: List<Characters>
) {
    data class Characters(
        val thumbnail: String?
    )
}
