package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviResult
import com.yk.marvelcomics.feature.home.ui.presentation.subview.CharactersDataView

sealed class DetailResult : MviResult {
    sealed class LoadPage : DetailResult() {
        data class Content(val detailComicDataView: DetailComicDataView, val charactersView: CharactersDataView) : LoadPage()
        data class Error(val throwable: Throwable) : LoadPage()
        object Loading : LoadPage()
    }
}
