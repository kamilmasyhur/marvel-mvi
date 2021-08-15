package com.yk.marvelcomics.feature.home.ui.viewmodel

import android.util.Log
import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.extension.exhaustive
import com.yk.marvelcomics.feature.home.domain.HomeActionProcessor
import com.yk.marvelcomics.feature.home.ui.state.HomeContentView
import com.yk.marvelcomics.feature.home.ui.state.MviHomeAction
import com.yk.marvelcomics.feature.home.ui.state.MviHomeIntent
import com.yk.marvelcomics.feature.home.ui.state.MviHomeResult
import com.yk.marvelcomics.feature.home.ui.state.MviHomeViewState
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    actionProcessor: HomeActionProcessor
) : MviBaseViewModel<MviHomeIntent, MviHomeAction, MviHomeResult, MviHomeViewState>(
    actionProcessor,
    MviHomeViewState.initiate()
) {
    override fun mapToActions(intent: MviHomeIntent): MviHomeAction {
        Log.d("HomeViewModel", "MapToAction: ${intent.javaClass.canonicalName}")
        return when (intent) {
            MviHomeIntent.InitiateHome -> MviHomeAction.InitiateHome
        }.exhaustive
    }

    override fun reduce(previousState: MviHomeViewState, result: MviHomeResult): MviHomeViewState {
        Log.d("MainViewModel", "Reducer: ${previousState.javaClass.canonicalName}")
        return when (result) {
            is MviHomeResult.InitiateHome.Content -> {
                previousState.copy(
                    loading = false,
                    content = HomeContentView(
                        comicsData = result.data.comicsData,
                        characters = result.data.characters,
                        events = result.data.events
                    ),
                    error = null
                )
            }
            is MviHomeResult.InitiateHome.Error -> {
                previousState.copy(
                    loading = false,
                    content = null,
                    error = result.throwable
                )
            }
            MviHomeResult.InitiateHome.Loading -> {
                previousState.copy(
                    loading = true,
                    content = null,
                    error = null
                )
            }
        }.exhaustive
    }
}
