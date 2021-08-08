package com.yk.marvelcomics.di.viewmodel

import androidx.lifecycle.ViewModelProvider
import com.yk.marvelcomics.base.presentation.viewmodel.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
