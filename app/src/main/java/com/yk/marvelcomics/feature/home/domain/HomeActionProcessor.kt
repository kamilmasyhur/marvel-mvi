package com.yk.marvelcomics.feature.home.domain

import com.yk.marvelcomics.base.domain.MviBaseActionProcessor
import com.yk.marvelcomics.common.MarvelScheduler
import com.yk.marvelcomics.feature.home.data.HomeRepository
import com.yk.marvelcomics.feature.home.domain.transformer.HomeTransformer
import com.yk.marvelcomics.feature.home.ui.state.MviHomeAction
import com.yk.marvelcomics.feature.home.ui.state.MviHomeResult
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class HomeActionProcessor @Inject constructor(
    private val repository: HomeRepository,
    private val homeTransformer: HomeTransformer,
    private val scheduler: MarvelScheduler
) : MviBaseActionProcessor<MviHomeAction, MviHomeResult>() {
    private val initialHomeProcessor =
        ObservableTransformer<MviHomeAction.InitiateHome, MviHomeResult.InitiateHome> { action ->
            action.flatMap {
                Single.zip(
                    repository.getComics(),
                    repository.getCharacters(),
                    repository.getEvents(), { comicsResponse, charactersResponse, eventsResponse ->
                        val result = homeTransformer.transform(
                            comicsResponse = comicsResponse,
                            charactersResponse = charactersResponse,
                            eventsResponse = eventsResponse
                        )
                        MviHomeResult.InitiateHome.Content(result)
                    })
                    .toObservable()
                    .subscribeOn(scheduler.io)
                    .observeOn(scheduler.main)
                    .cast(MviHomeResult.InitiateHome::class.java)
                    .onErrorReturn {
                        MviHomeResult.InitiateHome.Error(it)
                    }
            }
        }

    override val processActions: ObservableTransformer<MviHomeAction, MviHomeResult>
        get() = ObservableTransformer { action ->
            action.publish { source ->
                Observable.mergeArray(
                    source.ofType(MviHomeAction.InitiateHome::class.java)
                        .compose(initialHomeProcessor)
                )
            }
        }
}
