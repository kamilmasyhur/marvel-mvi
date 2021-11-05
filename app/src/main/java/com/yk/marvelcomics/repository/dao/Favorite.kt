package com.yk.marvelcomics.repository.dao

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query

const val FAVORITE_TABLE_NAME = "favorite_table"

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorite: Favorite)

    @Query("SELECT * FROM $FAVORITE_TABLE_NAME ORDER BY id ASC")
    fun getFavorite(): LiveData<List<Favorite>>

    @Query("SELECT * FROM $FAVORITE_TABLE_NAME WHERE type = :type")
    fun getFavoriteByType(type: String): LiveData<List<Favorite>>
}

@Entity(tableName = FAVORITE_TABLE_NAME)
data class Favorite(
    @PrimaryKey(autoGenerate = true) @NonNull @ColumnInfo(name = "id") val id: Long,
    @NonNull @ColumnInfo(name = "picture") val picture: String,
    @NonNull @ColumnInfo(name = "name") val name: String,
    @NonNull @ColumnInfo(name = "type") val type: String,
    @NonNull @ColumnInfo(name = "is_favorite") val isFavorite: Boolean
)
