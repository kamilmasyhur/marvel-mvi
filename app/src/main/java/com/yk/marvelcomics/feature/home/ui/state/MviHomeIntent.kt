package com.yk.marvelcomics.feature.home.ui.state

import com.yk.marvelcomics.base.MviIntent

sealed class MviHomeIntent: MviIntent {
    object InitiateHome: MviHomeIntent()
}
