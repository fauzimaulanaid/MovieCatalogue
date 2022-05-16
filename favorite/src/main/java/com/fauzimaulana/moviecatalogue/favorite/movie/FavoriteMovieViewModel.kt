package com.fauzimaulana.moviecatalogue.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase

class FavoriteMovieViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) : ViewModel() {
    fun getFavoriteMovies(): LiveData<List<NowPlayingMovie>> = movieCatalogueUseCase.getFavoriteMovies().asLiveData()

    fun setFavoriteMovie(movieEntity: NowPlayingMovie) {
        val newState = !movieEntity.favorite
        movieCatalogueUseCase.setFavoriteMovie(movieEntity, newState)
    }
}