package com.fauzimaulana.moviecatalogue.di

import com.fauzimaulana.moviecatalogue.core.domain.usecase.MovieCatalogueInteractor
import com.fauzimaulana.moviecatalogue.core.domain.usecase.MovieCatalogueUseCase
import com.fauzimaulana.moviecatalogue.detail.DetailViewModel
import com.fauzimaulana.moviecatalogue.movie.MovieViewModel
import com.fauzimaulana.moviecatalogue.tv.TvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieCatalogueUseCase> { MovieCatalogueInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}