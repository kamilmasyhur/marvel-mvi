package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviIntent

sealed class DetailIntent: MviIntent {
    data class LoadPage(val pageType: String, val detailId: Int) : DetailIntent()
    data class AddFavorite(val pageType: String, val detailId: Int): DetailIntent()
    data class RemoveFavorite(val pageType: String, val detailId: Int) : DetailIntent()
}
