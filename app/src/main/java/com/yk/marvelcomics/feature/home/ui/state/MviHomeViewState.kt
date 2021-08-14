package com.yk.marvelcomics.feature.home.ui.state

import com.yk.marvelcomics.base.MviViewState
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse
import com.yk.marvelcomics.feature.home.data.response.EventsResponse

data class HomeContentView(
    val comics: ComicsResponse,
    val characters: CharactersResponse,
    val events: EventsResponse,
)

sealed class MviHomeViewState: MviViewState {
    object ShowLoading:  MviHomeViewState()
    data class Content(val content: HomeContentView?): MviHomeViewState()
    object ConnectionError: MviHomeViewState()
}
