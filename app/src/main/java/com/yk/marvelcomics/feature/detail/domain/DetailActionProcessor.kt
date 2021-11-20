package com.yk.marvelcomics.feature.detail.domain

import com.yk.marvelcomics.base.domain.MviBaseActionProcessor
import com.yk.marvelcomics.common.MarvelScheduler
import com.yk.marvelcomics.feature.detail.data.DetailRepository
import com.yk.marvelcomics.feature.detail.presentation.*
import com.yk.marvelcomics.feature.home.domain.transformer.HomeTransformer
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DetailActionProcessor @Inject constructor(
    private val repository: DetailRepository,
    private val scheduler: MarvelScheduler,
    private val marvelMapper: HomeTransformer,
    private val favoriteDataProcessor: FavoriteDataProcessor
) : MviBaseActionProcessor<DetailAction, DetailResult>() {

    private val loadPageProcessor =
        ObservableTransformer<DetailAction.LoadPage, DetailResult.LoadPage> { action ->
            action.flatMap {
                return@flatMap when (it.pageType) {
                    DetailPageType.COMIC_PAGE.name -> observableDetailComic(it)
                    DetailPageType.CHARACTER_PAGE.name -> observableDetailHero(it)
                    DetailPageType.EVENT_PAGE.name -> observableDetailEvent(it)
                    else -> observableDetailComic(it)
                }
            }
        }

    private fun observableDetailEvent(it: DetailAction.LoadPage): ObservableSource<out DetailResult.LoadPage>? {
        return Single.zip(repository.getDetailEvent(it.detailId),
            repository.getCharactersByEventId(it.detailId),
            repository.getComicsByEventId(it.detailId),
            { detailEvent, characters, comics ->
                val eventDataView = marvelMapper.eventDetailMapper(detailEvent)
                val comicsView = marvelMapper.transformComics(comics)
                val charactersDataView = marvelMapper.transformCharacters(characters)
                val synopsisDataView = marvelMapper.synopsisMapper(eventDataView.description)
                DetailResult.LoadPage.EventContent(eventDataView, comicsView, charactersDataView, synopsisDataView)
            }
        ).toObservable()
            .subscribeOn(scheduler.io)
            .observeOn(scheduler.main)
            .cast(DetailResult.LoadPage::class.java)
            .onErrorReturn { throwable ->
                DetailResult.LoadPage.Error(throwable)
            }
    }

    private fun observableDetailHero(it: DetailAction.LoadPage): ObservableSource<out DetailResult.LoadPage>? {
        return Single.zip(repository.getDetailCharacter(it.detailId),
            repository.getComicsByCharacterId(it.detailId),
            { detailCharacter, comics ->
                val comicsView = marvelMapper.transformComics(comics)
                val detailDataCharacter = marvelMapper.characterDetailMapper(detailCharacter)
                val synopsisDataView = marvelMapper.synopsisMapper(detailDataCharacter.description)
                DetailResult.LoadPage.CharacterContent(
                    detailDataCharacter,
                    comicsView,
                    synopsisDataView
                )
            }
        ).toObservable()
            .subscribeOn(scheduler.io)
            .observeOn(scheduler.main)
            .cast(DetailResult.LoadPage::class.java)
            .onErrorReturn { throwable ->
                DetailResult.LoadPage.Error(throwable)
            }
    }

    private fun observableDetailComic(it: DetailAction.LoadPage) =
        Single.zip(repository.getDetailComic(it.detailId),
            repository.getCharacterByComicId(it.detailId),
            { detailComic, characters ->
                val detailComicView = marvelMapper.comicDetailMapper(detailComic)
                val charactersView = marvelMapper.transformCharacters(characters)
                val synopsisDataView = marvelMapper.synopsisMapper(detailComicView.description)
                val comicGallery = marvelMapper.comicGalleryMapper(detailComic)
                DetailResult.LoadPage.ComicContent(
                    detailComicView,
                    charactersView,
                    synopsisDataView,
                    comicGallery
                )
            }
        ).toObservable()
            .subscribeOn(scheduler.io)
            .observeOn(scheduler.main)
            .cast(DetailResult.LoadPage::class.java)
            .onErrorReturn { throwable ->
                DetailResult.LoadPage.Error(throwable)
            }

    override val processActions: ObservableTransformer<DetailAction, DetailResult>
        get() = ObservableTransformer { action ->
            action.publish { source ->
                Observable.mergeArray(
                    source.ofType(DetailAction.LoadPage::class.java).compose(loadPageProcessor),
                    source.ofType(DetailAction.AddFavorite::class.java).compose(favoriteDataProcessor.addFavoriteProcessor),
                    source.ofType(DetailAction.RemoveFavorite::class.java).compose(favoriteDataProcessor.removeFavoriteProcessor)
                )
            }
        }

}
