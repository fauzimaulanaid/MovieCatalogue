package com.fauzimaulana.moviecatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "on_air_tv")
data class OnAirTvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "year")
    val year: String,

    @ColumnInfo(name = "imagePath")
    val imagePath: String,

    @ColumnInfo(name = "genre")
    val genre: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false
)
