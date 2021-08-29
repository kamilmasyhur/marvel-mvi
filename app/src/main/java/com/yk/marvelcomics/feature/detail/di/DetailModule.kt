package com.yk.marvelcomics.feature.detail.di

import androidx.lifecycle.ViewModel
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelKey
import com.yk.marvelcomics.feature.detail.data.DetailRepository
import com.yk.marvelcomics.feature.detail.data.DetailRepositoryImpl
import com.yk.marvelcomics.feature.detail.viewmodel.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(ActivityComponent::class)
abstract class DetailModule {

    @Binds
    abstract fun bindRepository(impl: DetailRepositoryImpl): DetailRepository

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun provideViewModel(binds: DetailViewModel): ViewModel
}