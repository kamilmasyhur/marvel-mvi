package com.yk.marvelcomics.feature.main.presentation

import com.yk.marvelcomics.base.MviResult

sealed class MainResult: MviResult {
    data class SampleResult(val message: String): MainResult()
}
