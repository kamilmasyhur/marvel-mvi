package com.yk.marvelcomics.feature.main.domain

import com.yk.marvelcomics.base.domain.MviBaseActionProcessor
import com.yk.marvelcomics.feature.main.presentation.MainAction
import com.yk.marvelcomics.feature.main.presentation.MainResult
import io.reactivex.rxjava3.core.Observable.mergeArray
import io.reactivex.rxjava3.core.ObservableTransformer
import javax.inject.Inject

class MainActionProcessor @Inject constructor(): MviBaseActionProcessor<MainAction, MainResult>() {
    private val messageProcessor =
        ObservableTransformer<MainAction.SampleAction, MainResult.SampleResult> { action ->
            action.map {
                MainResult.SampleResult("message")
            }
        }

        override val processActions: ObservableTransformer<MainAction, MainResult>
        get() = ObservableTransformer { action ->
            action.publish { source ->
                mergeArray(
                    source.ofType(MainAction.SampleAction::class.java)
                        .compose(messageProcessor)
                )
            }
        }
}
