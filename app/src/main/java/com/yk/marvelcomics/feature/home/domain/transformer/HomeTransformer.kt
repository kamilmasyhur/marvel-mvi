package com.yk.marvelcomics.feature.home.domain.transformer

import com.yk.marvelcomics.common.getOrEmpty
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse
import com.yk.marvelcomics.feature.home.data.response.EventsResponse
import com.yk.marvelcomics.feature.home.ui.presentation.subview.ComicsDataView
import com.yk.marvelcomics.feature.home.ui.state.HomeContentView
import javax.inject.Inject

interface HomeTransformer {
    fun transform(
        comics: ComicsResponse,
        characters: CharactersResponse,
        events: EventsResponse
    ): HomeContentView
}

class HomeTransformerImpl @Inject constructor() : HomeTransformer {
    override fun transform(
        comicResponse: ComicsResponse,
        characters: CharactersResponse,
        events: EventsResponse
    ): HomeContentView {
        return HomeContentView(
            transformComics((comicResponse)),
            characters,
            events
        )
    }

    private fun transformComics(comicResponse: ComicsResponse): ComicsDataView {
        val comics = mutableListOf<ComicsDataView.Comic>()
        comicResponse.data?.results?.let { results ->
            results.forEach { result ->
                result?.let {
                    comics.add(
                        ComicsDataView.Comic(
                            title = result.title.getOrEmpty(),
                            creator = result.creators
                                ?.items
                                ?.firstNotNullOfOrNull { it?.name.toString() }.getOrEmpty(),
                            thumbnail = "${result.thumbnail?.path}.${result.thumbnail?.extension}"
                        )
                    )
                }
            }
        }

        return ComicsDataView(comics)
    }

}
