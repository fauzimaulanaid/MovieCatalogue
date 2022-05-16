package com.fauzimaulana.moviecatalogue.core.data.source.remote

import android.util.Log
import com.fauzimaulana.moviecatalogue.core.BuildConfig
import com.fauzimaulana.moviecatalogue.core.data.source.remote.network.ApiService
import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

class RemoteDataSource(private val apiService: ApiService){

    private val apiKey = BuildConfig.API_KEY

    fun getAllMovies(): Flow<ApiResponse<List<NowPlayingMovieResponse>>> {
        return flow {
            try {
                val response = apiService.getNowPlayingMovies(apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getAllTvShows(): Flow<ApiResponse<List<OnAirTvResponse>>> {
        return flow {
            try {
                val response = apiService.getOnAirTvShows(apiKey)
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovie(id: String): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovieDetail(id, apiKey)
                if (response.movieId != 0) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailTvShow(id: String): Flow<ApiResponse<TvResponse>> {
        return flow {
            try {
                val response = apiService.getTvShowDetail(id, apiKey)
                if (response.tvId != 0) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource: ", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}