package com.yk.marvelcomics.feature.home.domain.transformer

import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse
import com.yk.marvelcomics.feature.home.data.response.EventsResponse
import com.yk.marvelcomics.feature.home.ui.state.HomeContentView
import javax.inject.Inject

interface HomeTransformer {
    fun transform(
        comics: ComicsResponse,
        characters: CharactersResponse,
        events: EventsResponse
    ): HomeContentView
}
class HomeTransformerImpl @Inject constructor(): HomeTransformer {
    override fun transform(
        comics: ComicsResponse,
        characters: CharactersResponse,
        events: EventsResponse
    ): HomeContentView {
        return HomeContentView(comics, characters, events)
    }

}
