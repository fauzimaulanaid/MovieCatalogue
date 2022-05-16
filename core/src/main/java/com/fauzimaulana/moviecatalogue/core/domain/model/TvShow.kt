package com.fauzimaulana.moviecatalogue.core.domain.model

data class TvShow(
    val tvShowId: String,
    val title: String,
    val year: String,
    val genre: String,
    val imagePath: String,
    val rating: String,
    val runtime: String,
    val creator: String,
    val synopsys: String,
    val seasons: String,
    val firstAirDate: String,
    val language: String,
    val countriesOfOrigin: String,
    val episodes: String,
    val productionCompanies: String,
    val homepage: String
)
