package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviAction

sealed class DetailAction : MviAction {
    data class LoadPage(val pageType: String, val detailId: Int) : DetailAction()
    data class AddFavorite(val pageType: String, val detailId: Int, val prevState: DetailViewState?) : DetailAction()
    data class RemoveFavorite(val pageType: String, val detailId: Int, val prevState: DetailViewState?) : DetailAction()
}
