package com.fauzimaulana.moviecatalogue.core.domain.usecase

import com.fauzimaulana.moviecatalogue.core.domain.model.Movie
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.core.domain.model.TvShow
import com.fauzimaulana.moviecatalogue.core.domain.repository.IMovieCatalogueRepository
import com.fauzimaulana.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow

class MovieCatalogueInteractor(private val movieCatalogueRepository: IMovieCatalogueRepository): MovieCatalogueUseCase {
    override fun getAllMovies(): Flow<Resource<List<NowPlayingMovie>>> =
        movieCatalogueRepository.getAllMovies()

    override fun getSortedMovies(sort: String, tbName: String): Flow<List<NowPlayingMovie>> =
        movieCatalogueRepository.getSortedMovies(sort, tbName)

    override fun getAllTvShows(): Flow<Resource<List<OnAirTv>>> =
        movieCatalogueRepository.getAllTvShows()

    override fun getSortedTvShows(sort: String, tbName: String): Flow<List<OnAirTv>> =
        movieCatalogueRepository.getSortedTvShows(sort, tbName)

    override fun getFavoriteMovies(): Flow<List<NowPlayingMovie>> =
        movieCatalogueRepository.getFavoriteMovies()

    override fun setFavoriteMovie(movie: NowPlayingMovie, state: Boolean) =
        movieCatalogueRepository.setFavoriteMovie(movie, state)

    override fun getFavoriteTvShows(): Flow<List<OnAirTv>> =
        movieCatalogueRepository.getFavoriteTvShows()

    override fun setFavoriteTvShow(tvShow: OnAirTv, state: Boolean) =
        movieCatalogueRepository.setFavoriteTvShow(tvShow, state)

    override fun getMovie(movieId: String): Flow<NowPlayingMovie> =
        movieCatalogueRepository.getMovie(movieId)

    override fun getDetailMovie(id: String): Flow<Resource<Movie>> =
        movieCatalogueRepository.getDetailMovie(id)

    override fun getTvShow(tVShowId: String): Flow<OnAirTv> =
        movieCatalogueRepository.getTvShow(tVShowId)

    override fun getDetailTvShow(id: String): Flow<Resource<TvShow>> =
        movieCatalogueRepository.getDetailTvShow(id)
}