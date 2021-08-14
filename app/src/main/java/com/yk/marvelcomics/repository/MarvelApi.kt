package com.yk.marvelcomics.repository

import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse
import com.yk.marvelcomics.feature.home.data.response.EventsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface MarvelApi {
    @GET("/v1/public/comics")
    fun getComics(): Single<ComicsResponse>

    @GET("/v1/public/characters")
    fun getCharacters(): Single<CharactersResponse>

    @GET("/v1/public/events")
    fun getEvents(): Single<EventsResponse>
}
