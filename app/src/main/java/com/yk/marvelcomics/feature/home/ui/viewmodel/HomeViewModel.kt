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
    MviHomeViewState.ShowLoading
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
                MviHomeViewState.Content(
                    content = HomeContentView(
                        comics = result.data.comics,
                        characters = result.data.characters,
                        events = result.data.events
                    )
                )
            }
            is MviHomeResult.InitiateHome.Error -> {
                MviHomeViewState.ConnectionError
            }
            MviHomeResult.InitiateHome.Loading -> {
                MviHomeViewState.ShowLoading
            }
        }.exhaustive
    }
}
