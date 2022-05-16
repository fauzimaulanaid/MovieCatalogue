package com.fauzimaulana.moviecatalogue.core.data.source.local.room

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.NowPlayingMovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.OnAirTvEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieCatalogueDao {
    @Query("SELECT * FROM now_playing_movie")
    fun getMovies(): Flow<List<NowPlayingMovieEntity>>

    @Query("SELECT * FROM movie_entities WHERE movieId = :id")
    fun getMovieDetail(id: String): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetail(movie: MovieEntity)

    @Update
    fun updateMovieDetail(movie: MovieEntity)

    @RawQuery(observedEntities = [NowPlayingMovieEntity::class])
    fun getSortedMovies(query: SupportSQLiteQuery): Flow<List<NowPlayingMovieEntity>>

    @Query("SELECT * FROM now_playing_movie WHERE favorite = 1")
    fun getFavoriteMovies(): Flow<List<NowPlayingMovieEntity>>

    @Query("SELECT * FROM now_playing_movie WHERE movieId = :movieId")
    fun getMovieById(movieId: String): Flow<NowPlayingMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<NowPlayingMovieEntity>)

    @Update
    fun updateMovie(movie: NowPlayingMovieEntity)

    @Query("SELECT * FROM on_air_tv")
    fun getTvShows(): Flow<List<OnAirTvEntity>>

    @RawQuery(observedEntities = [OnAirTvEntity::class])
    fun getSortedTvShow(query: SupportSQLiteQuery): Flow<List<OnAirTvEntity>>

    @Query("SELECT * FROM on_air_tv WHERE favorite = 1")
    fun getFavoriteTvShows(): Flow<List<OnAirTvEntity>>

    @Query("SELECT * FROM on_air_tv WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: String): Flow<OnAirTvEntity>

    @Query("SELECT * FROM tv_show_entities WHERE tvShowId = :id")
    fun getTvShowDetail(id: String): Flow<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShowDetail(tvShow: TvEntity)

    @Update
    fun updateTvShowDetail(tvShow: TvEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvSHows(tvShows: List<OnAirTvEntity>)

    @Update
    fun updateTvShow(tvShow: OnAirTvEntity)
}