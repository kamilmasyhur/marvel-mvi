package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviViewState
import com.yk.marvelcomics.feature.home.ui.presentation.subview.CharactersDataView
import com.yk.marvelcomics.feature.home.ui.presentation.subview.ComicsDataView

data class DetailViewState(
    val loading: Boolean,
    val error: Throwable?,
    val detailComicDataView: DetailComicDataView?,
    val charactersDataView: CharactersDataView?,
    val synopsisDataView: DetailSynopsisDataView?,
    val detailCharacterDataView: DetailHeroDataView?,
    val comicsDataView: ComicsDataView?,
    val detailEventDataView: DetailEventDataView?,
    val isFavorite: Boolean = false,
) : MviViewState {
    companion object {
        fun initiate() = DetailViewState(true, null, null,
            null, null, null,
            null, null)
    }
}
