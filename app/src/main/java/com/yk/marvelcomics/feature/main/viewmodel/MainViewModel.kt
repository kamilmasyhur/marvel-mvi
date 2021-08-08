package com.yk.marvelcomics.feature.main.viewmodel

import android.util.Log
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.extension.exhaustive
import com.yk.marvelcomics.feature.main.domain.MainActionProcessor
import com.yk.marvelcomics.feature.main.presentation.MainAction
import com.yk.marvelcomics.feature.main.presentation.MainDataState
import com.yk.marvelcomics.feature.main.presentation.MainIntent
import com.yk.marvelcomics.feature.main.presentation.MainResult
import com.yk.marvelcomics.feature.main.presentation.MainViewState
import javax.inject.Inject

class MainViewModel @Inject constructor(
    processor: MainActionProcessor
) : MviBaseViewModel<MainIntent, MainAction, MainResult, MainViewState>(
    processor, MainViewState.InitialState(MainDataState.INITIAL_STATE)
) {
    override fun mapToActions(intent: MainIntent): MainAction {
        Log.d("MainViewModel", "MapToAction: ${intent.javaClass.canonicalName}")
        return when (intent) {
            MainIntent.SampleIntent -> MainAction.SampleAction
        }.exhaustive
    }

    override fun reduce(previousState: MainViewState, result: MainResult): MainViewState {
        val prevState = previousState.viewState
        Log.d("MainViewModel", "Reducer: ${prevState.javaClass.canonicalName}")
        return when (result) {
            is MainResult.SampleResult -> MainViewState.InitialState(
                prevState.copy(
                    loading = false,
                    message = result.message
                )
            )
        }.exhaustive
    }
}
