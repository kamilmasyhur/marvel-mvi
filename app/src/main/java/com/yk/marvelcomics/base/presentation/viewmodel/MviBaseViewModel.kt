package com.yk.marvelcomics.base.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.yk.marvelcomics.base.MviAction
import com.yk.marvelcomics.base.MviIntent
import com.yk.marvelcomics.base.MviResult
import com.yk.marvelcomics.base.MviViewState
import com.yk.marvelcomics.base.domain.MviBaseActionProcessor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.PublishSubject

interface MviViewModel<I : MviIntent, S : MviViewState> {
    fun processIntents(intents: Observable<I>) : Disposable
    fun state(): Observable<S>
}

abstract class MviBaseViewModel<I : MviIntent, A : MviAction, R : MviResult, S : MviViewState>(
    actionProcessor: MviBaseActionProcessor<A, R>, initialState: S
) : ViewModel(), MviViewModel<I, S> {

    private val intentsSubject = PublishSubject.create<I>()
    private val states = PublishSubject.create<S>()

    init {
        intentsSubject
            .scan(::intentFilter)
            .map(::mapToActions)
            .compose(actionProcessor.processActions)
            .scan(initialState, ::reduce)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(states)
    }

    override fun processIntents(intents: Observable<I>): Disposable =
        intents.subscribe(intentsSubject::onNext)

    override fun state(): Observable<S> = states.hide()

    override fun onCleared() {
        intentsSubject.onComplete()
        states.onComplete()
        super.onCleared()
    }

    open fun intentFilter(initialIntent: I, newIntent: I): I = newIntent

    abstract fun mapToActions(intent: I): A

    abstract fun reduce(previousState: S, result: R): S
}
