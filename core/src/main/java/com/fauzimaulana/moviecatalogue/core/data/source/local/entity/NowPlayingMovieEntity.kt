package com.fauzimaulana.moviecatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "now_playing_movie")
data class NowPlayingMovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: String,

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
