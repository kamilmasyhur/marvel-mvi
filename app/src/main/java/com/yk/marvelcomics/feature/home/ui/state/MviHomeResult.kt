package com.yk.marvelcomics.feature.home.ui.state

import com.yk.marvelcomics.base.MviResult

sealed class MviHomeResult: MviResult {
    sealed class InitiateHome: MviHomeResult() {
        object Loading: InitiateHome()
        data class Content(val data: HomeContentView): InitiateHome()
        data class Error(val throwable: Throwable): InitiateHome()
    }
}
