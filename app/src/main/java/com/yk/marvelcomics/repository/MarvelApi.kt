package com.yk.marvelcomics.repository

import com.yk.marvelcomics.feature.detail.data.response.DetailResponse
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse
import com.yk.marvelcomics.feature.home.data.response.EventsResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {
    @GET("/v1/public/comics")
    fun getComics(): Single<ComicsResponse>

    @GET("/v1/public/characters")
    fun getCharacters(): Single<CharactersResponse>

    @GET("/v1/public/events")
    fun getEvents(): Single<EventsResponse>

    @GET("/v1/public/comics/{comicId}")
    fun getDetailComic(
        @Path("comicId") comicId: Int
    ): Single<DetailResponse>

    @GET("/v1/public/comics/{comicId}/characters")
    fun getCharacterByComicId(
        @Path("comicId") comicId: Int
    ): Single<DetailResponse>

    @GET("/v1/public/characters/{characterId}")
    fun getDetailCharacter(
        @Path("characterId") characterId: Int
    ): Single<DetailResponse>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComicsByCharacterId(
        @Path("characterId") characterId: Int
    ): Single<DetailResponse>

    @GET("/v1/public/events/{eventId}")
    fun getDetailEvent(
        @Path("eventId") eventId: Int
    ): Single<DetailResponse>

    @GET("/v1/public/events/{eventId}/characters")
    fun getCharactersByEventId(
        @Path("eventId") eventId: Int
    ): Single<DetailResponse>

    @GET("/v1/public/events/{eventId}/comics")
    fun getComicsByEventId(
        @Path("eventId") eventId: Int
    ): Single<DetailResponse>

}
