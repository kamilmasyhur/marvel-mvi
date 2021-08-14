package com.yk.marvelcomics.feature.home.data

import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse
import com.yk.marvelcomics.feature.home.data.response.EventsResponse
import com.yk.marvelcomics.repository.MarvelApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface HomeRepository {
    fun getComics(): Single<ComicsResponse>

    fun getCharacters(): Single<CharactersResponse>

    fun getEvents(): Single<EventsResponse>
}

class HomeRepositoryImpl @Inject constructor(
    private val api: MarvelApi
): HomeRepository {
    override fun getComics(): Single<ComicsResponse> {
        return api.getComics()
    }

    override fun getCharacters(): Single<CharactersResponse> {
        return api.getCharacters()
    }

    override fun getEvents(): Single<EventsResponse> {
        return api.getEvents()
    }
}
