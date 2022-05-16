package com.fauzimaulana.moviecatalogue.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import com.fauzimaulana.moviecatalogue.core.vo.Resource

class MovieViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<NowPlayingMovie>>> = movieCatalogueUseCase.getAllMovies().asLiveData()

    fun getSortedMovies(sort: String, tbName: String): LiveData<List<NowPlayingMovie>> =
        movieCatalogueUseCase.getSortedMovies(sort, tbName).asLiveData()
}