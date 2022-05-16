package com.fauzimaulana.moviecatalogue.core.domain.model

data class NowPlayingMovie(
    val movieId: String,
    val title: String,
    val year: String,
    val imagePath: String,
    val genre: String,
    val favorite: Boolean
)
