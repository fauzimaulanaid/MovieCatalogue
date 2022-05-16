package com.fauzimaulana.moviecatalogue.core.di

import androidx.room.Room
import com.fauzimaulana.moviecatalogue.core.data.source.MovieCatalogueRepository
import com.fauzimaulana.moviecatalogue.core.data.source.local.LocalDataSource
import com.fauzimaulana.moviecatalogue.core.data.source.local.room.MovieCatalogueDatabase
import com.fauzimaulana.moviecatalogue.core.data.source.remote.RemoteDataSource
import com.fauzimaulana.moviecatalogue.core.data.source.remote.network.ApiService
import com.fauzimaulana.moviecatalogue.core.domain.repository.IMovieCatalogueRepository
import com.fauzimaulana.moviecatalogue.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieCatalogueDatabase>().movieCatalogueDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieCatalogueDatabase::class.java, "MovieCatalogue.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieCatalogueRepository> { MovieCatalogueRepository(get(), get(), get()) }
}