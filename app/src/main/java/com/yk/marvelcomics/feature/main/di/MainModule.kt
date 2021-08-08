package com.yk.marvelcomics.feature.main.di

import androidx.lifecycle.ViewModel
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelKey
import com.yk.marvelcomics.feature.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun provideViewModel(binds: MainViewModel): ViewModel
}
