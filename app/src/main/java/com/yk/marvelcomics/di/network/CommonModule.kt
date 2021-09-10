package com.yk.marvelcomics.di.network

import com.yk.marvelcomics.common.MarvelScheduler
import com.yk.marvelcomics.common.MarvelSchedulerImpl
import com.yk.marvelcomics.feature.home.domain.transformer.HomeTransformer
import com.yk.marvelcomics.feature.home.domain.transformer.HomeTransformerImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {
    @Singleton
    @Provides
    fun provideScheduler(impl: MarvelSchedulerImpl): MarvelScheduler {
        return impl
    }

    @Singleton
    @Provides
    fun bindHomeTransformer(impl: HomeTransformerImpl): HomeTransformer = impl

}
