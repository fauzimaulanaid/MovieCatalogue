package com.fauzimaulana.moviecatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_show_entities")
data class TvEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "year")
    var year: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "imagePath")
    var imagePath: String,

    @ColumnInfo(name = "rating")
    var rating: String,

    @ColumnInfo(name = "runtime")
    var runtime: String,

    @ColumnInfo(name = "creator")
    var creator: String,

    @ColumnInfo(name = "synopsys")
    var synopsys: String,

    @ColumnInfo(name = "seasons")
    var seasons: String,

    @ColumnInfo(name = "firstAirDate")
    var firstAirDate: String,

    @ColumnInfo(name = "language")
    var language: String,

    @ColumnInfo(name = "countriesOfOrigin")
    var countriesOfOrigin: String,

    @ColumnInfo(name = "episodes")
    var episodes: String,

    @ColumnInfo(name = "productionCompanies")
    var productionCompanies: String,

    @ColumnInfo(name = "homepage")
    var homepage: String
)
