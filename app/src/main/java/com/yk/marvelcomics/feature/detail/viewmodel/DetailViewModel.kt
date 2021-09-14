package com.yk.marvelcomics.feature.detail.viewmodel

import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.feature.detail.domain.DetailActionProcessor
import com.yk.marvelcomics.feature.detail.presentation.DetailAction
import com.yk.marvelcomics.feature.detail.presentation.DetailIntent
import com.yk.marvelcomics.feature.detail.presentation.DetailResult
import com.yk.marvelcomics.feature.detail.presentation.DetailViewState
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    detailActionProcessor: DetailActionProcessor
) : MviBaseViewModel<DetailIntent, DetailAction, DetailResult, DetailViewState>(
    detailActionProcessor,
    DetailViewState.initiate()
) {
    override fun mapToActions(intent: DetailIntent): DetailAction {
        return when(intent) {
            is DetailIntent.LoadPage -> DetailAction.LoadPage(intent.pageType, intent.detailId)
        }
    }

    override fun reduce(previousState: DetailViewState, result: DetailResult): DetailViewState {
        return when(result) {
            is DetailResult.LoadPage.ComicContent -> previousState.copy(
                loading = false,
                error = null,
                detailComicDataView = result.detailComicDataView,
                charactersDataView = result.charactersView,
                synopsisDataView = result.synopsisDataView,
                comicsDataView = result.comicGallery
            )
            is DetailResult.LoadPage.CharacterContent -> previousState.copy(
                loading = false,
                error = null,
                detailCharacterDataView = result.detailCharacterDataView,
                comicsDataView = result.comicsDataView,
                synopsisDataView = result.synopsisDataView,
            )
            is DetailResult.LoadPage.EventContent -> previousState.copy(
                loading = false,
                error = null,
                detailEventDataView = result.detailEventDataView,
                charactersDataView = result.charactersView,
                comicsDataView = result.comicsDataView,
                synopsisDataView = result.synopsisDataView,
            )
            is DetailResult.LoadPage.Error -> previousState.copy(
                loading = false,
                error = result.throwable,
                detailComicDataView = null,
                detailCharacterDataView = null,
                charactersDataView = null,
                synopsisDataView = null,
                comicsDataView = null
            )
            is DetailResult.LoadPage.Loading -> previousState.copy(
                loading = true,
                error = null,
                detailComicDataView = null,
                detailCharacterDataView = null,
                charactersDataView = null,
                synopsisDataView = null,
                comicsDataView = null
            )
        }
    }

}