package com.fauzimaulana.moviecatalogue.core.domain.usecase

import com.fauzimaulana.moviecatalogue.core.domain.model.Movie
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.core.domain.model.TvShow
import com.fauzimaulana.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow

interface MovieCatalogueUseCase {
    fun getAllMovies(): Flow<Resource<List<NowPlayingMovie>>>

    fun getSortedMovies(sort: String, tbName: String): Flow<List<NowPlayingMovie>>

    fun getAllTvShows(): Flow<Resource<List<OnAirTv>>>

    fun getSortedTvShows(sort: String, tbName: String): Flow<List<OnAirTv>>

    fun getFavoriteMovies(): Flow<List<NowPlayingMovie>>

    fun setFavoriteMovie(movie: NowPlayingMovie, state: Boolean)

    fun getFavoriteTvShows(): Flow<List<OnAirTv>>

    fun setFavoriteTvShow(tvShow: OnAirTv, state: Boolean)

    fun getMovie(movieId: String): Flow<NowPlayingMovie>

    fun getDetailMovie(id: String): Flow<Resource<Movie>>

    fun getTvShow(tVShowId: String): Flow<OnAirTv>

    fun getDetailTvShow(id: String): Flow<Resource<TvShow>>
}