package com.yk.marvelcomics.feature.detail.domain

import com.yk.marvelcomics.base.domain.MviBaseActionProcessor
import com.yk.marvelcomics.feature.detail.data.DetailRepository
import com.yk.marvelcomics.feature.detail.presentation.DetailAction
import com.yk.marvelcomics.feature.detail.presentation.DetailResult
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

class DetailActionProcessor @Inject constructor(
    private val repository: DetailRepository
) : MviBaseActionProcessor<DetailAction, DetailResult>() {
    override val processActions: ObservableTransformer<DetailAction, DetailResult>
        get() = TODO("Not yet implemented")

}