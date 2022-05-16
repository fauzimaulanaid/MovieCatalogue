package com.fauzimaulana.moviecatalogue.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import com.fauzimaulana.moviecatalogue.core.vo.Resource

class TvViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) : ViewModel() {
    fun getTvShow(): LiveData<Resource<List<OnAirTv>>> = movieCatalogueUseCase.getAllTvShows().asLiveData()

    fun getSortedTvShow(sort: String, tbName: String): LiveData<List<OnAirTv>> =
        movieCatalogueUseCase.getSortedTvShows(sort, tbName).asLiveData()
}