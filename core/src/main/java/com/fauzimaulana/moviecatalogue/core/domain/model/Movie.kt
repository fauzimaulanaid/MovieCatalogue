package com.fauzimaulana.moviecatalogue.core.domain.model

data class Movie(
    val movieId: String,
    val title: String,
    val year: String,
    val genre: String,
    val imagePath: String,
    val rating: String,
    val runtime: String,
    val tagline: String,
    val synopsys: String,
    val budget: String,
    val releaseDate: String,
    val language: String,
    val countriesOfOrigin: String,
    val revenue: String,
    val productionCompanies: String,
    val homepage: String
)
