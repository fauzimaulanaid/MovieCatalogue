package com.fauzimaulana.moviecatalogue.core.utils

import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.NowPlayingMovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.OnAirTvEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.TvEntity
import com.fauzimaulana.moviecatalogue.core.domain.model.Movie
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.core.domain.model.TvShow

object DataMapper {
    fun mapNowPlayingMovieEntitiesToDomain(input: List<NowPlayingMovieEntity>): List<NowPlayingMovie> =
        input.map {
            NowPlayingMovie(
                movieId = it.movieId,
                title = it.title,
                year = it.year,
                imagePath = it.imagePath,
                genre = it.genre,
                favorite = it.favorite
            )
        }

    fun mapDetailNowPlayingMovieEntityToDomain(input: NowPlayingMovieEntity) = NowPlayingMovie(
        movieId = input.movieId,
        title = input.title,
        year = input.year,
        imagePath = input.imagePath,
        genre = input.genre,
        favorite = input.favorite
    )

    fun mapNowPlayingMovieDomainToEntity(input: NowPlayingMovie) = NowPlayingMovieEntity(
        movieId = input.movieId,
        title =input.title,
        year = input.year,
        imagePath = input.imagePath,
        genre = input.genre,
        favorite = input.favorite
    )

    fun mapOnAirTvEntitiesToDomain(input: List<OnAirTvEntity>): List<OnAirTv> =
        input.map {
            OnAirTv(
                tvShowId = it.tvShowId,
                title = it.title,
                year = it.year,
                imagePath = it.imagePath,
                genre = it.genre,
                favorite = it.favorite
            )
        }

    fun mapDetailOnAirTvEntityToDomain(input: OnAirTvEntity) = OnAirTv(
        tvShowId = input.tvShowId,
        title = input.title,
        year = input.year,
        imagePath = input.imagePath,
        genre = input.genre,
        favorite = input.favorite
    )

    fun mapOnAirTvDomainToEntity(input: OnAirTv) = OnAirTvEntity(
        tvShowId = input.tvShowId,
        title = input.title,
        year = input.year,
        imagePath = input.imagePath,
        genre = input.genre,
        favorite = input.favorite
    )

    fun mapMovieEntityToDomain(input: MovieEntity?) = input?.let {
        Movie(
        movieId = it.movieId,
        title = input.title,
        year = input.year,
        genre = input.genre,
        imagePath = input.imagePath,
        rating = input.rating,
        runtime = input.runtime,
        tagline = input.tagline,
        synopsys = input.synopsys,
        budget = input.budget,
        releaseDate = input.releaseDate,
        language = input.language,
        countriesOfOrigin = input.countriesOfOrigin,
        revenue = input.revenue,
        productionCompanies = input.productionCompanies,
        homepage = input.homepage
    )
    }

    fun mapTvEntityToDomain(input: TvEntity?) = input?.let {
        TvShow(
        tvShowId = it.tvShowId,
        title = input.title,
        year = input.year,
        genre = input.genre,
        imagePath = input.imagePath,
        rating = input.rating,
        runtime = input.runtime,
        creator = input.creator,
        synopsys = input.synopsys,
        seasons = input.seasons,
        firstAirDate = input.firstAirDate,
        language = input.language,
        countriesOfOrigin = input.countriesOfOrigin,
        episodes = input.episodes,
        productionCompanies = input.productionCompanies,
        homepage = input.homepage
    )
    }
}