package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviViewState

data class DetailViewState(
    val loading: Boolean,
    val error: Throwable?
) : MviViewState {
    companion object {
        fun initiate() = DetailViewState(true, null)
    }
}
