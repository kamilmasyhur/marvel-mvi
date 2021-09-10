package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviViewState
import com.yk.marvelcomics.feature.home.ui.presentation.subview.CharactersDataView

data class DetailViewState(
    val loading: Boolean,
    val error: Throwable?,
    val detailComicDataView: DetailComicDataView?,
    val detailCharacterDataView: CharactersDataView?
) : MviViewState {
    companion object {
        fun initiate() = DetailViewState(true, null, null, null)
    }
}
