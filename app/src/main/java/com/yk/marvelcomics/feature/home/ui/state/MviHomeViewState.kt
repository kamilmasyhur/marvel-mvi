package com.yk.marvelcomics.feature.home.ui.state

import com.yk.marvelcomics.base.MviViewState
import com.yk.marvelcomics.feature.home.data.response.EventsResponse
import com.yk.marvelcomics.feature.home.ui.presentation.subview.CharactersDataView
import com.yk.marvelcomics.feature.home.ui.presentation.subview.ComicsDataView
import com.yk.marvelcomics.feature.home.ui.presentation.subview.EventsDataView

data class HomeContentView(
    val comicsData: ComicsDataView,
    val characters: CharactersDataView,
    val events: EventsDataView,
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
