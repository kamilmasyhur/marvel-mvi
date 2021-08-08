package com.yk.marvelcomics.feature.main.presentation

import com.yk.marvelcomics.base.MviViewState

data class MainDataState(
    val loading: Boolean = false,
    val message: String = ""
) {
    companion object {
        val INITIAL_STATE = MainDataState(
            loading = false
        )

        val EMPTY = MainDataState()
    }
}

sealed class MainViewState(val viewState: MainDataState): MviViewState {
    data class InitialState(val data: MainDataState) : MainViewState(data)
}
