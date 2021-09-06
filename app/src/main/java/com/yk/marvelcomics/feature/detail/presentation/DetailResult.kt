package com.yk.marvelcomics.feature.detail.presentation

import com.yk.marvelcomics.base.MviResult

sealed class DetailResult : MviResult {
    sealed class LoadPage : DetailResult() {
        data class Content(val dataViews: List<DetailDataView>) : LoadPage()
        data class Error(val throwable: Throwable) : LoadPage()
        object Loading : LoadPage()
    }
}
