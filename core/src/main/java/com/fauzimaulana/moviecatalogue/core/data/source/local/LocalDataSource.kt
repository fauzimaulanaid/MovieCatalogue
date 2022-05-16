package com.fauzimaulana.moviecatalogue.core.data.source.local

import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.NowPlayingMovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.OnAirTvEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.TvEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.room.MovieCatalogueDao
import com.fauzimaulana.moviecatalogue.core.utils.SortUtils
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mMovieCatalogueDao: MovieCatalogueDao){
    fun getAllMovies(): Flow<List<NowPlayingMovieEntity>> = mMovieCatalogueDao.getMovies()

    fun getSortedMovies(sort: String, tbName: String): Flow<List<NowPlayingMovieEntity>> {
        val query = SortUtils.getSortedQuery(sort, tbName)
        return mMovieCatalogueDao.getSortedMovies(query)
    }

    fun getFavoriteMovies(): Flow<List<NowPlayingMovieEntity>> = mMovieCatalogueDao.getFavoriteMovies()

    fun getDetailMovie(movieId: String): Flow<NowPlayingMovieEntity> = mMovieCatalogueDao.getMovieById(movieId)

    fun getMovieDetail(id: String): Flow<MovieEntity> = mMovieCatalogueDao.getMovieDetail(id)

    suspend fun insertMovieDetail(movie: MovieEntity) = mMovieCatalogueDao.insertMovieDetail(movie)

    suspend fun insertMovies(movies: List<NowPlayingMovieEntity>) = mMovieCatalogueDao.insertMovies(movies)

    fun setMovieFavorite(movie: NowPlayingMovieEntity, newState: Boolean) {
        movie.favorite = newState
        mMovieCatalogueDao.updateMovie(movie)
    }

    fun getAllTvShows(): Flow<List<OnAirTvEntity>> = mMovieCatalogueDao.getTvShows()

    fun getSortedTvShows(sort: String, tbName: String): Flow<List<OnAirTvEntity>> {
        val query = SortUtils.getSortedQuery(sort, tbName)
        return mMovieCatalogueDao.getSortedTvShow(query)
    }

    fun getFavoriteTvShows(): Flow<List<OnAirTvEntity>> = mMovieCatalogueDao.getFavoriteTvShows()

    fun getDetailTvShow(tvShowId: String):Flow<OnAirTvEntity> = mMovieCatalogueDao.getTvShowById(tvShowId)

    fun getTvShowDetail(id: String): Flow<TvEntity> = mMovieCatalogueDao.getTvShowDetail(id)

    suspend fun insertTvShowDetail(tvShow: TvEntity) = mMovieCatalogueDao.insertTvShowDetail(tvShow)

    suspend fun insertTvShows(tvShows: List<OnAirTvEntity>) = mMovieCatalogueDao.insertTvSHows(tvShows)

    fun setTvShowFavorite(tvShow: OnAirTvEntity, newState: Boolean) {
        tvShow.favorite = newState
        mMovieCatalogueDao.updateTvShow(tvShow)
    }
}