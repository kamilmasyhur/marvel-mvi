package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviResult
import com.yk.marvelcomics.feature.home.ui.presentation.subview.CharactersDataView
import com.yk.marvelcomics.feature.home.ui.presentation.subview.ComicsDataView

sealed class DetailResult : MviResult {
    sealed class LoadPage : DetailResult() {
        data class ComicContent(
            val detailComicDataView: DetailComicDataView,
            val charactersView: CharactersDataView,
            val synopsisDataView: DetailSynopsisDataView
        ) : LoadPage()

        data class CharacterContent(
            val detailCharacterDataView: DetailHeroDataView,
            val comicsDataView: ComicsDataView,
            val synopsisDataView: DetailSynopsisDataView
        ) : LoadPage()

        data class EventContent(
            val detailEventDataView: DetailEventDataView,
            val comicsDataView: ComicsDataView,
            val charactersView: CharactersDataView,
            val synopsisDataView: DetailSynopsisDataView
        ) : LoadPage()

        data class Error(val throwable: Throwable) : LoadPage()

        object Loading : LoadPage()
    }
}
