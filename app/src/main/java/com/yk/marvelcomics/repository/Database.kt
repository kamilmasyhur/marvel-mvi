package com.yk.marvelcomics.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yk.marvelcomics.repository.dao.Favorite
import com.yk.marvelcomics.repository.dao.FavoriteDao

const val DATABASE_NAME = "marvel-mvi"
const val DATABASE_VERSION = 1

@Database(entities = [Favorite::class], version = DATABASE_VERSION)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
