package com.yk.marvelcomics.di.network

import android.content.Context
import androidx.room.Room
import com.yk.marvelcomics.repository.DATABASE_NAME
import com.yk.marvelcomics.repository.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteDao(@ApplicationContext context: Context): MarvelDatabase = Room.databaseBuilder(
        context,
        MarvelDatabase::class.java,
        DATABASE_NAME
    ).build()

}
