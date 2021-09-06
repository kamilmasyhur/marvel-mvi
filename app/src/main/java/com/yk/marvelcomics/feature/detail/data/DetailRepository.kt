package com.yk.marvelcomics.feature.detail.data

import com.yk.marvelcomics.feature.detail.data.response.DetailResponse
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.repository.MarvelApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Path
import javax.inject.Inject

interface DetailRepository {
    fun getDetailComic(comicId: Int): Single<DetailResponse>
    fun getCharacterByComicId(comicId: Int): Single<CharactersResponse>
    fun getDetailCharacter(characterId: Int): Single<DetailResponse>
    fun getComicsByCharacterId(characterId: Int): Single<DetailResponse>
    fun getDetailEvent(eventId: Int): Single<DetailResponse>
    fun getCharactersByEventId(eventId: Int): Single<DetailResponse>
    fun getComicsByEventId(eventId: Int): Single<DetailResponse>
}

class DetailRepositoryImpl @Inject constructor(
    private val api: MarvelApi
) : DetailRepository {
    override fun getDetailComic(comicId: Int): Single<DetailResponse> {
        return api.getDetailComic(comicId)
    }

    override fun getCharacterByComicId(comicId: Int): Single<CharactersResponse> {
        return api.getCharacterByComicId(comicId)
    }

    override fun getDetailCharacter(characterId: Int): Single<DetailResponse> {
        return api.getDetailCharacter(characterId)
    }

    override fun getComicsByCharacterId(characterId: Int): Single<DetailResponse> {
        return api.getComicsByCharacterId(characterId)
    }

    override fun getDetailEvent(eventId: Int): Single<DetailResponse> {
        return api.getDetailEvent(eventId)
    }

    override fun getCharactersByEventId(eventId: Int): Single<DetailResponse> {
        return api.getCharactersByEventId(eventId)
    }

    override fun getComicsByEventId(eventId: Int): Single<DetailResponse> {
        return api.getComicsByEventId(eventId)
    }

}