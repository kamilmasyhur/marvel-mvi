package com.yk.marvelcomics.feature.main.presentation

import com.yk.marvelcomics.base.MviIntent

sealed class MainIntent: MviIntent {
    object SampleIntent: MainIntent()
}
