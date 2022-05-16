package com.fauzimaulana.moviecatalogue.favorite.di

import com.fauzimaulana.moviecatalogue.favorite.movie.FavoriteMovieViewModel
import com.fauzimaulana.moviecatalogue.favorite.tv.FavoriteTvViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvViewModel(get()) }
}