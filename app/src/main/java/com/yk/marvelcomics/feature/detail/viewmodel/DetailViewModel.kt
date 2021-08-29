package com.yk.marvelcomics.feature.detail.viewmodel

import com.yk.marvelcomics.base.presentation.viewmodel.MviBaseViewModel
import com.yk.marvelcomics.feature.detail.domain.DetailActionProcessor
import com.yk.marvelcomics.feature.detail.presentation.DetailAction
import com.yk.marvelcomics.feature.detail.presentation.DetailIntent
import com.yk.marvelcomics.feature.detail.presentation.DetailResult
import com.yk.marvelcomics.feature.detail.presentation.DetailViewState
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    detailActionProcessor: DetailActionProcessor
) : MviBaseViewModel<DetailIntent, DetailAction, DetailResult, DetailViewState>(
    detailActionProcessor,
    DetailViewState.initiate()
) {
    override fun mapToActions(intent: DetailIntent): DetailAction {
        TODO("Not yet implemented")
    }

    override fun reduce(previousState: DetailViewState, result: DetailResult): DetailViewState {
        TODO("Not yet implemented")
    }

}