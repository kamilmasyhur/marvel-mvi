package com.yk.marvelcomics.feature.detail.data

import androidx.annotation.WorkerThread
import com.yk.marvelcomics.feature.detail.data.response.DetailResponse
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.data.response.ComicsResponse
import com.yk.marvelcomics.repository.MarvelApi
import com.yk.marvelcomics.repository.MarvelDatabase
import com.yk.marvelcomics.repository.dao.Favorite
import com.yk.marvelcomics.repository.dao.FavoriteDao
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface DetailRepository {
    fun getDetailComic(comicId: Int): Single<DetailResponse>
    fun getCharacterByComicId(comicId: Int): Single<CharactersResponse>
    fun getDetailCharacter(characterId: Int): Single<DetailResponse>
    fun getComicsByCharacterId(characterId: Int): Single<ComicsResponse>
    fun getDetailEvent(eventId: Int): Single<DetailResponse>
    fun getCharactersByEventId(eventId: Int): Single<CharactersResponse>
    fun getComicsByEventId(eventId: Int): Single<ComicsResponse>

    fun insertFavorite(favorite: Favorite): Long
    fun removeFavorite(favorite: Favorite)
}

class DetailRepositoryImpl @Inject constructor(
    private val api: MarvelApi,
    private val db: MarvelDatabase
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

    override fun getComicsByCharacterId(characterId: Int): Single<ComicsResponse> {
        return api.getComicsByCharacterId(characterId)
    }

    override fun getDetailEvent(eventId: Int): Single<DetailResponse> {
        return api.getDetailEvent(eventId)
    }

    override fun getCharactersByEventId(eventId: Int): Single<CharactersResponse> {
        return api.getCharactersByEventId(eventId)
    }

    override fun getComicsByEventId(eventId: Int): Single<ComicsResponse> {
        return api.getComicsByEventId(eventId)
    }

    @WorkerThread
    override fun insertFavorite(favorite: Favorite): Long {
        return db.favoriteDao().insert(favorite)
    }

    @WorkerThread
    override fun removeFavorite(favorite: Favorite) {
        return db.favoriteDao().remove(favorite)
    }
}
