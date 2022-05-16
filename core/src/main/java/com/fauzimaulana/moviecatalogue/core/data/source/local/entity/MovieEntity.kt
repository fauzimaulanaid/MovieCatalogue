package com.fauzimaulana.moviecatalogue.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: String,

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

    @ColumnInfo(name = "tagline")
    var tagline: String,

    @ColumnInfo(name = "synopsys")
    var synopsys: String,

    @ColumnInfo(name = "budget")
    var budget: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "language")
    var language: String,

    @ColumnInfo(name = "countriesOfOrigin")
    var countriesOfOrigin: String,

    @ColumnInfo(name = "revenue")
    var revenue: String,

    @ColumnInfo(name = "productionCompanies")
    var productionCompanies: String,

    @ColumnInfo(name = "homepage")
    var homepage: String
)
