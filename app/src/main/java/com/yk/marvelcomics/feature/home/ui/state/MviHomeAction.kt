package com.yk.marvelcomics.feature.home.ui.state

import com.yk.marvelcomics.base.MviAction

sealed class MviHomeAction: MviAction {
    object InitiateHome: MviHomeAction()
}
