package com.fauzimaulana.moviecatalogue.core.data.source

import com.fauzimaulana.moviecatalogue.core.data.source.local.LocalDataSource
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.NowPlayingMovieEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.OnAirTvEntity
import com.fauzimaulana.moviecatalogue.core.data.source.local.entity.TvEntity
import com.fauzimaulana.moviecatalogue.core.data.source.remote.ApiResponse
import com.fauzimaulana.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.*
import com.fauzimaulana.moviecatalogue.core.domain.model.Movie
import com.fauzimaulana.moviecatalogue.core.domain.model.NowPlayingMovie
import com.fauzimaulana.moviecatalogue.core.domain.model.OnAirTv
import com.fauzimaulana.moviecatalogue.core.domain.model.TvShow
import com.fauzimaulana.moviecatalogue.core.domain.repository.IMovieCatalogueRepository
import com.fauzimaulana.moviecatalogue.core.utils.AppExecutors
import com.fauzimaulana.moviecatalogue.core.utils.DataMapper
import com.fauzimaulana.moviecatalogue.core.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.StringBuilder
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MovieCatalogueRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
    ): IMovieCatalogueRepository {

    override fun getAllMovies(): Flow<Resource<List<NowPlayingMovie>>> {
        return object : NetworkBoundResource<List<NowPlayingMovie>, List<NowPlayingMovieResponse>>() {
            override fun loadFromDB(): Flow<List<NowPlayingMovie>?> =
                localDataSource.getAllMovies().map { DataMapper.mapNowPlayingMovieEntitiesToDomain(it) }

            override fun shouldFetch(data: List<NowPlayingMovie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<NowPlayingMovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<NowPlayingMovieResponse>) {
                val movieList = ArrayList<NowPlayingMovieEntity>()

                for (response in data) {
                    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val formatter = LocalDate.parse(response.year, dateFormat)

                    val genres = StringBuilder()
                    val listGenre = response.genre
                    if (listGenre.isEmpty()) {
                        genres.append("Unknown")
                    } else {
                        for (j in listGenre.indices) {
                            if (j == listGenre.size - 1) {
                                when (listGenre[j]) {
                                    28 -> genres.append("Action")
                                    12 -> genres.append("Adventure")
                                    16 -> genres.append("Animation")
                                    35 -> genres.append("Comedy")
                                    80 -> genres.append("Crime")
                                    99 -> genres.append("Documentary")
                                    18 -> genres.append("Drama")
                                    10751 -> genres.append("Family")
                                    14 -> genres.append("Fantasy")
                                    36 -> genres.append("History")
                                    27 -> genres.append("Horror")
                                    10402 -> genres.append("Music")
                                    9648 -> genres.append("Mystery")
                                    10749 -> genres.append("Romance")
                                    878 -> genres.append("Science Fiction")
                                    10770 -> genres.append("TV Movie")
                                    53 -> genres.append("Thriller")
                                    10752 -> genres.append("War")
                                    37 -> genres.append("Western")
                                }
                            } else {
                                when (listGenre[j]) {
                                    28 -> genres.append("Action, ")
                                    12 -> genres.append("Adventure, ")
                                    16 -> genres.append("Animation, ")
                                    35 -> genres.append("Comedy, ")
                                    80 -> genres.append("Crime, ")
                                    99 -> genres.append("Documentary, ")
                                    18 -> genres.append("Drama, ")
                                    10751 -> genres.append("Family, ")
                                    14 -> genres.append("Fantasy, ")
                                    36 -> genres.append("History, ")
                                    27 -> genres.append("Horror, ")
                                    10402 -> genres.append("Music, ")
                                    9648 -> genres.append("Mystery, ")
                                    10749 -> genres.append("Romance, ")
                                    878 -> genres.append("Science Fiction, ")
                                    10770 -> genres.append("TV Movie, ")
                                    53 -> genres.append("Thriller, ")
                                    10752 -> genres.append("War, ")
                                    37 -> genres.append("Western, ")
                                }
                            }
                        }
                    }

                    val movie = NowPlayingMovieEntity(
                        response.movieId.toString(),
                        response.title,
                        formatter.year.toString(),
                        "https://image.tmdb.org/t/p/original${response.imagePath}",
                        genres.toString(),
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()
    }

    override fun getSortedMovies(sort: String, tbName: String): Flow<List<NowPlayingMovie>> =
        localDataSource.getSortedMovies(sort, tbName).map { DataMapper.mapNowPlayingMovieEntitiesToDomain(it) }

    override fun getAllTvShows(): Flow<Resource<List<OnAirTv>>> {
        return object : NetworkBoundResource<List<OnAirTv>, List<OnAirTvResponse>>() {
            override fun loadFromDB(): Flow<List<OnAirTv>?> =
                localDataSource.getAllTvShows().map { DataMapper.mapOnAirTvEntitiesToDomain(it) }

            override fun shouldFetch(data: List<OnAirTv>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<OnAirTvResponse>>> =
                remoteDataSource.getAllTvShows()

            override suspend fun saveCallResult(data: List<OnAirTvResponse>) {
                val tvShowList = ArrayList<OnAirTvEntity>()
                for (response in data) {
                    val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val formatter = LocalDate.parse(response.year, dateFormat)

                    val genres = StringBuilder()
                    val listGenre = response.genre
                    if (listGenre.isEmpty()) {
                        genres.append("Unknown")
                    } else {
                        for (j in listGenre.indices) {
                            if (j == listGenre.size - 1) {
                                when (listGenre[j]) {
                                    10759 -> genres.append("Action & Adventure")
                                    10762 -> genres.append("Kids")
                                    10763 -> genres.append("News")
                                    10764 -> genres.append("Reality")
                                    10765 -> genres.append("Sci-Fi & Fantasy")
                                    10766 -> genres.append("Soap")
                                    10767 -> genres.append("Talk")
                                    10768 -> genres.append("War & Politics")
                                    16 -> genres.append("Animation")
                                    35 -> genres.append("Comedy")
                                    80 -> genres.append("Crime")
                                    99 -> genres.append("Documentary")
                                    18 -> genres.append("Drama")
                                    10751 -> genres.append("Family")
                                    9648 -> genres.append("Mystery")
                                    37 -> genres.append("Western")
                                }
                            } else {
                                when (listGenre[j]) {
                                    10759 -> genres.append("Action & Adventure, ")
                                    10762 -> genres.append("Kids, ")
                                    10763 -> genres.append("News, ")
                                    10764 -> genres.append("Reality,")
                                    10765 -> genres.append("Sci-Fi & Fantasy, ")
                                    10766 -> genres.append("Soap, ")
                                    10767 -> genres.append("Talk, ")
                                    10768 -> genres.append("War & Politics, ")
                                    16 -> genres.append("Animation, ")
                                    35 -> genres.append("Comedy, ")
                                    80 -> genres.append("Crime, ")
                                    99 -> genres.append("Documentary, ")
                                    18 -> genres.append("Drama, ")
                                    10751 -> genres.append("Family, ")
                                    9648 -> genres.append("Mystery, ")
                                    37 -> genres.append("Western, ")
                                }
                            }
                        }
                    }

                    val tvShow = OnAirTvEntity(
                        response.tvShowId.toString(),
                        response.title,
                        formatter.year.toString(),
                        "https://image.tmdb.org/t/p/original${response.imagePath}",
                        genres.toString(),
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()
    }

    override fun getSortedTvShows(sort: String, tbName: String): Flow<List<OnAirTv>> =
        localDataSource.getSortedTvShows(sort, tbName).map { DataMapper.mapOnAirTvEntitiesToDomain(it) }

    override fun getFavoriteMovies(): Flow<List<NowPlayingMovie>> =
        localDataSource.getFavoriteMovies().map { DataMapper.mapNowPlayingMovieEntitiesToDomain(it) }

    override fun setFavoriteMovie(movie: NowPlayingMovie, state: Boolean) {
        val movieEntity = DataMapper.mapNowPlayingMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }

    override fun getFavoriteTvShows(): Flow<List<OnAirTv>> =
        localDataSource.getFavoriteTvShows().map { DataMapper.mapOnAirTvEntitiesToDomain(it) }

    override fun setFavoriteTvShow(tvShow: OnAirTv, state: Boolean) {
        val tvShowEntity = DataMapper.mapOnAirTvDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShowEntity, state) }
    }

    override fun getMovie(movieId: String): Flow<NowPlayingMovie> =
        localDataSource.getDetailMovie(movieId).map { DataMapper.mapDetailNowPlayingMovieEntityToDomain(it) }

    override fun getDetailMovie(id: String): Flow<Resource<Movie>> {
        return object: NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie?> =
                localDataSource.getMovieDetail(id).map { DataMapper.mapMovieEntityToDomain(it) }

            override fun shouldFetch(data: Movie?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> =
                remoteDataSource.getDetailMovie(id)

            override suspend fun saveCallResult(data: MovieResponse) {

                val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formatter = LocalDate.parse(data.releaseDate, dateFormat)

                val genres = StringBuilder()
                val listGenre = data.genre
                if (listGenre.isEmpty()) {
                    genres.append("Unknown")
                } else {
                    for (j in listGenre.indices) {
                        if (j == listGenre.size - 1) {
                            genres.append(listGenre[j].name)
                        } else {
                            genres.append("${listGenre[j].name}, ")
                        }
                    }
                }

                val rating = StringBuilder()
                if (data.rating == 0.0) {
                    rating.append("Unknown")
                } else {
                    rating.append("${data.rating}/10")
                }

                val runtime = StringBuilder()
                if (data.runtime == 0) {
                    runtime.append("Unknown")
                } else {
                    runtime.append("${data.runtime} minutes")
                }

                val tagline = StringBuilder()
                if (data.tagline == "") {
                    tagline.append("Unknown")
                } else {
                    tagline.append("\"${data.tagline}\"")
                }

                val synopsys = StringBuilder()
                if (data.synopsys == "") {
                    synopsys.append("Unknown")
                } else {
                    synopsys.append(data.synopsys)
                }

                val budget = StringBuilder()
                if (data.budget == 0) {
                    budget.append("Unknown")
                } else {
                    budget.append("${data.budget} USD")
                }

                val releaseDate = StringBuilder()
                if (data.releaseDate == "") {
                    releaseDate.append("Unknown")
                } else {
                    releaseDate.append(data.releaseDate)
                }

                val revenue = StringBuilder()
                if (data.revenue == 0) {
                    revenue.append("Unknown")
                } else {
                    revenue.append("${data.revenue} USD")
                }

                val languages = StringBuilder()
                val listLanguage = data.language
                if (listLanguage.isEmpty()) {
                    languages.append("Unknown")
                } else {
                    for (j in listLanguage.indices) {
                        if (j == listLanguage.size - 1) {
                            languages.append(listLanguage[j].englishName)
                        } else {
                            languages.append("${listLanguage[j].englishName}, ")
                        }
                    }
                }

                val countries = StringBuilder()
                val listOfCountry = data.countriesOfOrigin
                if (listOfCountry.isEmpty()) {
                    countries.append("Unknown")
                } else {
                    for (j in listOfCountry.indices) {
                        if (j == listOfCountry.size - 1) {
                            countries.append(listOfCountry[j].name)
                        } else {
                            countries.append("${listOfCountry[j].name}, ")
                        }
                    }
                }

                val companies = StringBuilder()
                val listOfCompany = data.productionCompanies
                if (listOfCompany.isEmpty()) {
                    companies.append("Unknown")
                } else {
                    for (j in listOfCompany.indices) {
                        if (j == listOfCompany.size -1) {
                            companies.append(listOfCompany[j].name)
                        } else {
                            companies.append("${listOfCompany[j].name}, ")
                        }
                    }
                }

                val homepage = StringBuilder()
                if (data.homepage == "") {
                    homepage.append("Unknown")
                } else {
                    homepage.append(data.homepage)
                }

                val movie = MovieEntity(
                    data.movieId.toString(),
                    data.title,
                    formatter.year.toString(),
                    genres.toString(),
                    "https://image.tmdb.org/t/p/original${data.imagePath}",
                    rating.toString(),
                    runtime.toString(),
                    tagline.toString(),
                    synopsys.toString(),
                    budget.toString(),
                    releaseDate.toString(),
                    languages.toString(),
                    countries.toString(),
                    revenue.toString(),
                    companies.toString(),
                    homepage.toString()
                )
                localDataSource.insertMovieDetail(movie)
            }
        }.asFlow()
    }

    override fun getTvShow(tvShowId: String): Flow<OnAirTv> =
        localDataSource.getDetailTvShow(tvShowId).map { DataMapper.mapDetailOnAirTvEntityToDomain(it) }

    override fun getDetailTvShow(id: String): Flow<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvResponse>() {
            override fun loadFromDB(): Flow<TvShow?> =
                localDataSource.getTvShowDetail(id).map { DataMapper.mapTvEntityToDomain(it) }

            override fun shouldFetch(data: TvShow?): Boolean =
                data == null

            override suspend fun createCall(): Flow<ApiResponse<TvResponse>> =
                remoteDataSource.getDetailTvShow(id)

            override suspend fun saveCallResult(data: TvResponse) {
                val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formatter = LocalDate.parse(data.firstAirDate, dateFormat)

                val genres = StringBuilder()
                val listGenre = data.genre
                if (listGenre.isEmpty()) {
                    genres.append("Unknown")
                } else {
                    for (j in listGenre.indices) {
                        if (j == listGenre.size - 1) {
                            genres.append(listGenre[j].name)
                        } else {
                            genres.append("${listGenre[j].name}, ")
                        }
                    }
                }

                val rating = StringBuilder()
                if (data.rating == 0.0) {
                    rating.append("Unknown")
                } else {
                    rating.append("${data.rating}/10")
                }

                var runtime = 0
                for (j in data.runtime.indices) {
                    runtime += data.runtime[j]
                }

                val runtimeOfTvShow = StringBuilder()
                if (runtime == 0) {
                    runtimeOfTvShow.append("Unknown")
                } else {
                    runtimeOfTvShow.append("$runtime minutes")
                }

                val creators = StringBuilder()
                val listOfCreator = data.creator
                if (listOfCreator.isEmpty()) {
                    creators.append("Unknown")
                } else {
                    for (j in listOfCreator.indices) {
                        if (j == listOfCreator.size - 1) {
                            creators.append(listOfCreator[j].name)
                        } else {
                            creators.append("${listOfCreator[j].name}, ")
                        }
                    }
                }

                val synopsys = StringBuilder()
                if (data.synopsys == "") {
                    synopsys.append("Unknown")
                } else {
                    synopsys.append(data.synopsys)
                }

                val seasons = StringBuilder()
                if (data.seasons == 0) {
                    seasons.append("Unknown")
                } else {
                    seasons.append("${data.seasons} Seasons")
                }

                val firstAirDate = StringBuilder()
                if (data.firstAirDate == "") {
                    firstAirDate.append("Unknown")
                } else {
                    firstAirDate.append(data.firstAirDate)
                }

                val languages = StringBuilder()
                val listOfLanguage = data.language
                if (listOfLanguage.isEmpty()) {
                    languages.append("Unknown")
                } else {
                    for (j in listOfLanguage.indices) {
                        if (j == listOfLanguage.size - 1) {
                            languages.append(listOfLanguage[j].englishName)
                        } else {
                            languages.append("${listOfLanguage[j].englishName}, ")
                        }
                    }
                }

                val countries = StringBuilder()
                val listOfCountry = data.countriesOfOrigin
                if (listOfCountry.isEmpty()) {
                    countries.append("Unknown")
                } else {
                    for (j in listOfCountry.indices) {
                        if (j == listOfCountry.size -1) {
                            countries.append(listOfCountry[j].name)
                        } else {
                            countries.append("${listOfCountry[j].name}, ")
                        }
                    }
                }

                val episodes = StringBuilder()
                if (data.episodes == 0) {
                    episodes.append("Unknown")
                } else {
                    episodes.append("${data.episodes} Episodes")
                }

                val companies = StringBuilder()
                val listOfCompany = data.productionCompanies
                if (listOfCompany.isEmpty()) {
                    companies.append("Unknown")
                } else {
                    for (j in listOfCompany.indices) {
                        if (j == listOfCompany.size -1) {
                            companies.append(listOfCompany[j].name)
                        } else {
                            companies.append("${listOfCompany[j].name}, ")
                        }
                    }
                }

                val homepage = StringBuilder()
                if (data.homepage == "") {
                    homepage.append("Unknown")
                } else {
                    homepage.append(data.homepage)
                }

                val tvShow = TvEntity(
                    data.tvId.toString(),
                    data.title,
                    formatter.year.toString(),
                    genres.toString(),
                    "https://image.tmdb.org/t/p/original${data.imagePath}",
                    rating.toString(),
                    runtimeOfTvShow.toString(),
                    creators.toString(),
                    synopsys.toString(),
                    seasons.toString(),
                    firstAirDate.toString(),
                    languages.toString(),
                    countries.toString(),
                    episodes.toString(),
                    companies.toString(),
                    homepage.toString()
                )
                localDataSource.insertTvShowDetail(tvShow)
            }
        }.asFlow()
    }
}