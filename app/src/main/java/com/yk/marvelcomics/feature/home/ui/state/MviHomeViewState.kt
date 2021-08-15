package com.yk.marvelcomics.feature.home.ui.state

import com.yk.marvelcomics.base.MviViewState
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.EventsResponse
import com.yk.marvelcomics.feature.home.ui.presentation.subview.ComicsDataView

data class HomeContentView(
    val comicsData: ComicsDataView,
    val characters: CharactersResponse,
    val events: EventsResponse,
)

data class MviHomeViewState(
    val loading: Boolean,
    val content: HomeContentView?,
    val error: Throwable?
) : MviViewState {
    companion object {
        fun initiate() = MviHomeViewState(
            loading = true,
            content = null,
            error = null
        )
    }
}
