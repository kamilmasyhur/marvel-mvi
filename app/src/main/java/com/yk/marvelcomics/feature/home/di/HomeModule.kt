package com.yk.marvelcomics.feature.home.di

import androidx.lifecycle.ViewModel
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelKey
import com.yk.marvelcomics.feature.home.data.HomeRepository
import com.yk.marvelcomics.feature.home.data.HomeRepositoryImpl
import com.yk.marvelcomics.feature.home.domain.transformer.HomeTransformer
import com.yk.marvelcomics.feature.home.domain.transformer.HomeTransformerImpl
import com.yk.marvelcomics.feature.home.ui.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(FragmentComponent::class)
interface HomeModule {

    @Binds
    fun bindRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun bindHomeTransformer(impl: HomeTransformerImpl): HomeTransformer

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun provideViewModel(binds: HomeViewModel): ViewModel
}
