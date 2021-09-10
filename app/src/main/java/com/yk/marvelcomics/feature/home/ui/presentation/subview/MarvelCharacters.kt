package com.yk.marvelcomics.feature.home.ui.presentation.subview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.yk.marvelcomics.databinding.MarvelCharactersViewBinding
import com.yk.marvelcomics.feature.detail.presentation.DetailDataView
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

    fun addCharactersItem(comicDataView: CharactersDataView, listener: CharacterListener? = null) {
        adapter.initItems(comicDataView.characters, listener)
        binding.tvSeeAll.setOnClickListener {
            listener?.onSeeAllCharacterClick()
        }
    }
}

data class CharactersDataView(
    val characters: List<Characters>
) : DetailDataView {
    data class Characters(
        val thumbnail: String?,
        val id: Int?
    )
}

interface CharacterListener {
    fun onCharacterClick(result: CharactersDataView.Characters)
    fun onSeeAllCharacterClick()
}
