package com.fauzimaulana.moviecatalogue.detail

import androidx.lifecycle.*
import com.fauzimaulana.moviecatalogue.core.domain.model.Movie
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.core.domain.model.TvShow
import com.fauzimaulana.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import com.fauzimaulana.moviecatalogue.core.vo.Resource

class DetailViewModel(private val movieCatalogueUseCase: MovieCatalogueUseCase): ViewModel() {

    private val dataId = MutableLiveData<String>()

    fun selectedItem(dataId: String) {
        this.dataId.value = dataId
    }


    var getMovie: LiveData<NowPlayingMovie> = Transformations.switchMap(dataId) { mMovieId ->
        movieCatalogueUseCase.getMovie(mMovieId).asLiveData()
    }

    var getDetailMovie: LiveData<Resource<Movie>> = Transformations.switchMap(dataId) { movieId ->
        movieCatalogueUseCase.getDetailMovie(movieId).asLiveData()
    }

    var getDetailTvShow: LiveData<Resource<TvShow>> = Transformations.switchMap(dataId) { tvShowId ->
        movieCatalogueUseCase.getDetailTvShow(tvShowId).asLiveData()
    }

    var getTvShow: LiveData<OnAirTv> = Transformations.switchMap(dataId) { mTvShowId ->
        movieCatalogueUseCase.getTvShow(mTvShowId).asLiveData()
    }

    fun setFavoriteMovie() {
        val movieEntity = getMovie.value
        if (movieEntity != null) {
            val newState = !movieEntity.favorite
            movieCatalogueUseCase.setFavoriteMovie(movieEntity, newState)
        }
    }

    fun setFavoriteTvShow() {
        val tvShowEntity = getTvShow.value
        if (tvShowEntity != null) {
            val newState = !tvShowEntity.favorite
            movieCatalogueUseCase.setFavoriteTvShow(tvShowEntity, newState)
        }
    }
}