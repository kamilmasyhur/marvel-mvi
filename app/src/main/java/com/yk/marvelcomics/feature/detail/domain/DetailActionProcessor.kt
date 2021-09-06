package com.yk.marvelcomics.feature.detail.domain

import com.yk.marvelcomics.base.domain.MviBaseActionProcessor
import com.yk.marvelcomics.common.MarvelScheduler
import com.yk.marvelcomics.feature.detail.data.DetailRepository
import com.yk.marvelcomics.feature.detail.data.response.DetailResponse
import com.yk.marvelcomics.feature.detail.presentation.*
import com.yk.marvelcomics.feature.home.data.response.CharactersResponse
import com.yk.marvelcomics.feature.home.domain.transformer.HomeTransformer
import com.yk.marvelcomics.feature.home.ui.presentation.subview.CharactersDataView
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DetailActionProcessor @Inject constructor(
    private val repository: DetailRepository,
    private val scheduler: MarvelScheduler,
    private val marvelMapper: HomeTransformer
) : MviBaseActionProcessor<DetailAction, DetailResult>() {

    private val loadPageProcessor =
        ObservableTransformer<DetailAction.LoadPage, DetailResult.LoadPage> { action ->
            action.flatMap {
                observableDetailComic(it)
                return@flatMap when (it.pageType) {
                    DetailPageType.COMIC_PAGE.name -> observableDetailComic(it)
                    DetailPageType.HERO_PAGE.name -> observableDetailHero(it)
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
            { detailCharacter,  comics ->

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
                DetailResult.LoadPage.Content(listOf(detailComicView, charactersView))
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
                    source.ofType(DetailAction.LoadPage::class.java).compose(loadPageProcessor)
                )
            }
        }

}