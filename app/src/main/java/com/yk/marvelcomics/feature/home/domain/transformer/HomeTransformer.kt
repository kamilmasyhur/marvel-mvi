package com.yk.marvelcomics.feature.home.domain.transformer

import com.yk.marvelcomics.common.getOrEmpty
import com.yk.marvelcomics.feature.detail.data.response.DetailResponse
import com.yk.marvelcomics.feature.detail.presentation.DetailComicDataView
import com.yk.marvelcomics.feature.detail.presentation.DetailEventDataView
import com.yk.marvelcomics.feature.detail.presentation.DetailHeroDataView
import com.yk.marvelcomics.feature.detail.presentation.DetailSynopsisDataView
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

    fun transformComics(comicResponse: ComicsResponse): ComicsDataView
    fun transformCharacters(charactersResponse: CharactersResponse): CharactersDataView
    fun transformEvents(eventsResponse: EventsResponse): EventsDataView
    fun comicDetailMapper(detailComic: DetailResponse): DetailComicDataView
    fun synopsisMapper(synopsis: String?): DetailSynopsisDataView
    fun characterDetailMapper(detailCharacter: DetailResponse): DetailHeroDataView
    fun eventDetailMapper(detailEvent: DetailResponse?): DetailEventDataView
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

    override fun transformComics(comicResponse: ComicsResponse): ComicsDataView {
        val comics = mutableListOf<ComicsDataView.Comic>()
        comicResponse.data?.results?.map { result ->
            result?.let {
                comics.add(
                    ComicsDataView.Comic(
                        title = result.title.getOrEmpty(),
                        creator = result.creators
                            ?.items
                            ?.firstOrNull { it?.name.toString().isNotEmpty() }
                            ?.name.orEmpty(),
                        thumbnail = "${result.thumbnail?.path}.${result.thumbnail?.extension}",
                        id = result.id
                    )
                )
            }
        }
        return ComicsDataView(comics)
    }

    override fun transformCharacters(charactersResponse: CharactersResponse): CharactersDataView {
        val characters = mutableListOf<CharactersDataView.Characters>()
        charactersResponse.data?.results?.map { result ->
            result?.thumbnail?.let {
                characters.add(
                    CharactersDataView.Characters(
                        thumbnail = "${result.thumbnail.path}.${result.thumbnail.extension}",
                        id = result.id
                    )
                )
            }
        }
        return CharactersDataView(characters)
    }


    override fun transformEvents(eventsResponse: EventsResponse): EventsDataView {
        val events = mutableListOf<EventsDataView.Event>()
        eventsResponse.data?.results?.map { result ->
            result?.let {
                events.add(
                    EventsDataView.Event(
                        title = result.title.getOrEmpty(),
                        description = result.description.getOrEmpty(),
                        thumbnail = "${result.thumbnail?.path}.${result.thumbnail?.extension}",
                        id = result.id
                    )
                )
            }
        }
        return EventsDataView(events)
    }

    override fun comicDetailMapper(detailComic: DetailResponse): DetailComicDataView {
        val detailDataComic = detailComic.data?.results?.firstOrNull()
        val creators: List<String>? = detailDataComic?.creators?.items?.map {
            it?.name.orEmpty()
        }
        val price = "$${detailDataComic?.prices?.firstOrNull()?.price}"
        val image = "${detailDataComic?.thumbnail?.path}.${detailDataComic?.thumbnail?.extension}"
        return DetailComicDataView(
            title = detailDataComic?.title, description = detailDataComic?.description,
            creators = creators, price = price, image = image
        )
    }

    override fun synopsisMapper(synopsis: String?) = DetailSynopsisDataView(synopsis)

    override fun characterDetailMapper(detailCharacter: DetailResponse): DetailHeroDataView {
        val detailData = detailCharacter.data?.results?.firstOrNull()
        val image = "${detailData?.thumbnail?.path}.${detailData?.thumbnail?.extension}"
        val name = detailData?.name
        val description = detailData?.description
        val date = detailData?.modified
        return DetailHeroDataView(image, name, description, date)
    }

    override fun eventDetailMapper(detailEvent: DetailResponse?): DetailEventDataView {
        val detailData = detailEvent?.data?.results?.firstOrNull()
        val title = detailData?.title
        val image = "${detailData?.thumbnail?.path}.${detailData?.thumbnail?.extension}"
        val description = detailData?.description
        val date = detailData?.modified
        return DetailEventDataView(title, description, date, image)
    }
}
