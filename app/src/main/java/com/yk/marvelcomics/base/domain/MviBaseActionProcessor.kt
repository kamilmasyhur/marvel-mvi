package com.yk.marvelcomics.base.domain

import com.yk.marvelcomics.base.MviAction
import com.yk.marvelcomics.base.MviResult
import io.reactivex.rxjava3.core.ObservableTransformer

abstract class MviBaseActionProcessor<A : MviAction, R : MviResult> {
    abstract val processActions: ObservableTransformer<A, R>
}
