package com.yk.marvelcomics.base

import io.reactivex.rxjava3.core.Observable

interface MviIntent
interface MviAction
interface MviResult
interface MviViewState

interface MviView<I : MviIntent, S : MviViewState> {
    fun intents(): Observable<I>
    fun render(state: S)
}
