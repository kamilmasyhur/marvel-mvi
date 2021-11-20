package com.yk.marvelcomics.feature.detail.viewmodel

import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.feature.detail.domain.DetailActionProcessor
import com.yk.marvelcomics.feature.detail.presentation.DetailAction
import com.yk.marvelcomics.feature.detail.presentation.DetailIntent
import com.yk.marvelcomics.feature.detail.presentation.DetailResult
import com.yk.marvelcomics.feature.detail.presentation.DetailViewState
import com.yk.marvelcomics.repository.dao.Favorite
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    detailActionProcessor: DetailActionProcessor
) : MviBaseViewModel<DetailIntent, DetailAction, DetailResult, DetailViewState>(
    detailActionProcessor,
    DetailViewState.initiate()
) {
    private var prevState: DetailViewState? = null

    override fun mapToActions(intent: DetailIntent): DetailAction {
        return when(intent) {
            is DetailIntent.LoadPage -> DetailAction.LoadPage(intent.pageType, intent.detailId)
            is DetailIntent.AddFavorite -> DetailAction.AddFavorite(intent.pageType, intent.detailId, prevState)
            is DetailIntent.RemoveFavorite -> DetailAction.RemoveFavorite(intent.pageType, intent.detailId, prevState)
        }
    }

    override fun reduce(previousState: DetailViewState, result: DetailResult): DetailViewState {
        prevState = when(result) {
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
            is DetailResult.FavoriteData.AddFavoriteSuccess -> previousState.copy(
                isFavorite = true
            )
            is DetailResult.FavoriteData.Error -> previousState.copy(
                isFavorite = false
            )
            DetailResult.FavoriteData.RemoveFavoriteSuccess -> previousState.copy(
                isFavorite = false
            )
        }
        return prevState as DetailViewState
    }

}
