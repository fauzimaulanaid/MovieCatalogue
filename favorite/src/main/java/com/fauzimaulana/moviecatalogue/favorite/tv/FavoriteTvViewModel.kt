package com.fauzimaulana.moviecatalogue.favorite.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase

class FavoriteTvViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) : ViewModel() {
    fun getFavoriteTvShows(): LiveData<List<OnAirTv>> = movieCatalogueUseCase.getFavoriteTvShows().asLiveData()

    fun setFavoriteTvShow(tvShowEntity: OnAirTv) {
        val newState = !tvShowEntity.favorite
        movieCatalogueUseCase.setFavoriteTvShow(tvShowEntity, newState)
    }
}