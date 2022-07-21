package com.yk.marvelcomics.feature.detail.domain

import com.yk.marvelcomics.common.MarvelScheduler
import com.yk.marvelcomics.feature.detail.data.DetailRepository
import com.yk.marvelcomics.feature.detail.presentation.DetailAction
import com.yk.marvelcomics.feature.detail.presentation.DetailPageType
import com.yk.marvelcomics.feature.detail.presentation.DetailResult
import com.yk.marvelcomics.repository.dao.Favorite
import com.yk.marvelcomics.repository.dao.INSERT_ERROR_FAVORITE_NULL
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

class FavoriteDataProcessor @Inject constructor(
    private val repository: DetailRepository,
    private val scheduler: MarvelScheduler,
) {
    val removeFavoriteProcessor =
        ObservableTransformer<DetailAction.RemoveFavorite, DetailResult.FavoriteData> { action ->
            action.flatMap { data ->
                val favorite = when (data.pageType) {
                    DetailPageType.COMIC_PAGE.name -> createFavoriteComic(data, data.detailId)
                    DetailPageType.CHARACTER_PAGE.name -> createFavoriteCharacter(
                        data,
                        data.detailId
                    )
                    DetailPageType.EVENT_PAGE.name -> createFavoriteEvent(data, data.detailId)
                    else -> createFavoriteComic(data, data.detailId)
                }
                favorite?.let {
                    return@let Observable.fromCallable { repository.removeFavorite(favorite) }
                        .map { DetailResult.FavoriteData.RemoveFavoriteSuccess }
                        .subscribeOn(scheduler.io)
                        .observeOn(scheduler.main)
                        .cast(DetailResult.FavoriteData::class.java)
                        .onErrorReturn { throwable ->
                            DetailResult.FavoriteData.Error(throwable)
                        }
                } ?: run {
                    Observable.just(DetailResult.FavoriteData.Error(Throwable(INSERT_ERROR_FAVORITE_NULL)))
                }
            }
        }

    val addFavoriteProcessor =
        ObservableTransformer<DetailAction.AddFavorite, DetailResult.FavoriteData> { action ->
            action.flatMap { data ->
                val favorite = when (data.pageType) {
                    DetailPageType.COMIC_PAGE.name -> createFavoriteComic(data, data.detailId)
                    DetailPageType.CHARACTER_PAGE.name -> createFavoriteCharacter(
                        data,
                        data.detailId
                    )
                    DetailPageType.EVENT_PAGE.name -> createFavoriteEvent(data, data.detailId)
                    else -> createFavoriteComic(data, data.detailId)
                }
                favorite?.let {
                    return@let Observable.fromCallable { repository.insertFavorite(favorite) }
                        .map { DetailResult.FavoriteData.RemoveFavoriteSuccess }
                        .subscribeOn(scheduler.io)
                        .observeOn(scheduler.main)
                        .cast(DetailResult.FavoriteData::class.java)
                        .onErrorReturn { throwable ->
                            DetailResult.FavoriteData.Error(throwable)
                        }
                } ?: run {
                    Observable.just(DetailResult.FavoriteData.Error(Throwable(INSERT_ERROR_FAVORITE_NULL)))
                }
            }
        }

    private fun createFavoriteComic(data: DetailAction.AddFavorite, id: Int): Favorite? {
        val detailComic = data.prevState?.detailComicDataView
        return detailComic?.let {
            Favorite(
                id = id,
                picture = detailComic.image.orEmpty(),
                name = detailComic.title.orEmpty(),
                type = data.pageType,
                isFavorite = true
            )
        }
    }

    private fun createFavoriteCharacter(data: DetailAction.AddFavorite, id: Int): Favorite? {
        val detailCharacter = data.prevState?.detailCharacterDataView
        return detailCharacter?.let {
            Favorite(
                id = id,
                picture = detailCharacter.image.orEmpty(),
                name = detailCharacter.name.orEmpty(),
                type = data.pageType,
                isFavorite = true
            )
        }
    }

    private fun createFavoriteEvent(data: DetailAction.AddFavorite, id: Int): Favorite? {
        val detailEvent = data.prevState?.detailEventDataView
        return detailEvent?.let {
            Favorite(
                id = id,
                picture = detailEvent.image.orEmpty(),
                name = detailEvent.title.orEmpty(),
                type = data.pageType,
                isFavorite = true
            )
        }
    }

    private fun createFavoriteComic(data: DetailAction.RemoveFavorite, id: Int): Favorite? {
        val detailComic = data.prevState?.detailComicDataView
        return detailComic?.let {
            Favorite(
                id = id,
                picture = detailComic.image.orEmpty(),
                name = detailComic.title.orEmpty(),
                type = data.pageType,
                isFavorite = false
            )
        }
    }

    private fun createFavoriteCharacter(data: DetailAction.RemoveFavorite, id: Int): Favorite? {
        val detailCharacter = data.prevState?.detailCharacterDataView
        return detailCharacter?.let {
            Favorite(
                id = id,
                picture = detailCharacter.image.orEmpty(),
                name = detailCharacter.name.orEmpty(),
                type = data.pageType,
                isFavorite = false
            )
        }
    }

    private fun createFavoriteEvent(data: DetailAction.RemoveFavorite, id: Int): Favorite? {
        val detailEvent = data.prevState?.detailEventDataView
        return detailEvent?.let {
            Favorite(
                id = id,
                picture = detailEvent.image.orEmpty(),
                name = detailEvent.title.orEmpty(),
                type = data.pageType,
                isFavorite = false
            )
        }
    }

}
