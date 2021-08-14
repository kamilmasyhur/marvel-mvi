package com.yk.marvelcomics.di.network

import com.yk.marvelcomics.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        CommonModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object AppModule {
}
