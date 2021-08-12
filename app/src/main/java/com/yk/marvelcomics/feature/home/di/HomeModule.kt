package com.yk.marvelcomics.feature.home.di

import androidx.lifecycle.ViewModel
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelKey
import com.yk.marvelcomics.feature.home.data.HomeRepository
import com.yk.marvelcomics.feature.home.data.HomeRepositoryImpl
import com.yk.marvelcomics.feature.home.domain.HomeUseCase
import com.yk.marvelcomics.feature.home.domain.HomeUseCaseImpl
import com.yk.marvelcomics.feature.home.ui.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindUseCase(impl: HomeUseCaseImpl): HomeUseCase

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideViewModel(binds: HomeViewModel): ViewModel
}
