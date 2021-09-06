package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviViewState

data class DetailViewState(
    val loading: Boolean,
    val error: Throwable?,
    val dataViews: List<DetailDataView>?
) : MviViewState {
    companion object {
        fun initiate() = DetailViewState(true, null, null)
    }
}
