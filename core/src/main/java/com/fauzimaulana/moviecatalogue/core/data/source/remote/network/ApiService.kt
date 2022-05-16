package com.fauzimaulana.moviecatalogue.core.data.source.remote.network

import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.ListNowPlayingMovieResponse
import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.ListOnAirTvResponse
import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.MovieResponse
import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.TvResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") apikey: String): ListNowPlayingMovieResponse

    @GET("tv/on_the_air")
    suspend fun getOnAirTvShows(@Query("api_key") apikey: String): ListOnAirTvResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") id: String, @Query("api_key") apikey: String): MovieResponse

    @GET("tv/{tv_id}")
    suspend fun getTvShowDetail(@Path("tv_id") id: String, @Query("api_key") apikey: String): TvResponse
}