package com.yk.marvelcomics.feature.main.presentation

import com.yk.marvelcomics.base.MviAction

sealed class MainAction: MviAction {
    object SampleAction: MainAction()
}
