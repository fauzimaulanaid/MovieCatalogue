package com.fauzimaulana.moviecatalogue.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.NowPlayingMovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.OnAirTvEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.TvEntity

@Database(entities = [NowPlayingMovieEntity::class, OnAirTvEntity::class,
    MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class MovieCatalogueDatabase: RoomDatabase() {
    abstract fun movieCatalogueDao(): MovieCatalogueDao
}