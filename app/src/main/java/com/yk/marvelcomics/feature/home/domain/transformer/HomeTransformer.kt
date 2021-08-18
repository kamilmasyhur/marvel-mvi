package com.yk.marvelcomics.feature.home.domain.transformer

import com.yk.marvelcomics.common.getOrEmpty
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse
import com.yk.marvelcomics.feature.home.data.response.EventsResponse
import com.yk.marvelcomics.feature.home.ui.presentation.subview.CharactersDataView
import com.yk.marvelcomics.feature.home.ui.presentation.subview.ComicsDataView
import com.yk.marvelcomics.feature.home.ui.presentation.subview.EventsDataView
import com.yk.marvelcomics.feature.home.ui.state.HomeContentView
import javax.inject.Inject

interface HomeTransformer {
    fun transform(
        comicsResponse: ComicsResponse,
        charactersResponse: CharactersResponse,
        eventsResponse: EventsResponse
    ): HomeContentView
}

class HomeTransformerImpl @Inject constructor() : HomeTransformer {
    override fun transform(
        comicsResponse: ComicsResponse,
        charactersResponse: CharactersResponse,
        eventsResponse: EventsResponse
    ): HomeContentView {
        return HomeContentView(
            transformComics(comicsResponse),
            transformCharacters(charactersResponse),
            transformEvents(eventsResponse)
        )
    }

    private fun transformComics(comicResponse: ComicsResponse): ComicsDataView {
        val comics = mutableListOf<ComicsDataView.Comic>()
        comicResponse.data?.results?.map { result ->
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
        return ComicsDataView(comics)
    }

    private fun transformCharacters(charactersResponse: CharactersResponse): CharactersDataView {
        val characters = mutableListOf<CharactersDataView.Characters>()
        charactersResponse.data?.results?.map { result ->
            result?.thumbnail?.let {
                characters.add(
                    CharactersDataView.Characters(
                        thumbnail = "${result.thumbnail.path}.${result.thumbnail.extension}"
                    )
                )
            }
        }
        return CharactersDataView(characters)
    }


    private fun transformEvents(eventsResponse: EventsResponse): EventsDataView {
        val events = mutableListOf<EventsDataView.Event>()
        eventsResponse.data?.results?.map { result ->
            result?.let {
                events.add(
                    EventsDataView.Event(
                        title = result.title.getOrEmpty(),
                        description = result.description.getOrEmpty(),
                        thumbnail = "${result.thumbnail?.path}.${result.thumbnail?.extension}"
                    )
                )
            }
        }
        return EventsDataView(events)
    }
}
