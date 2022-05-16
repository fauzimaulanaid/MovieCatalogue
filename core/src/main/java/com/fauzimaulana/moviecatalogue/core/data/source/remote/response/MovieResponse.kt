package com.fauzimaulana.moviecatalogue.core.data.source.remote.response

import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item.GenresItem
import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item.ProductionCompaniesItem
import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item.ProductionCountriesItem
import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item.SpokenLanguagesItem
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    var movieId: Int,

    @field:SerializedName("title")
    var title: String,

    @field:SerializedName("genres")
    var genre: List<GenresItem>, //string

    @field:SerializedName("poster_path")
    var imagePath: String,

    @field:SerializedName("vote_average")
    var rating: Double,

    @field:SerializedName("runtime")
    var runtime: Int,

    @field:SerializedName("tagline")
    var tagline: String,

    @field:SerializedName("overview")
    var synopsys: String,

    @field:SerializedName("budget")
    var budget: Int,

    @field:SerializedName("release_date")
    var releaseDate: String,

    @field:SerializedName("spoken_languages")
    var language: List<SpokenLanguagesItem>, //String

    @field:SerializedName("production_countries")
    var countriesOfOrigin: List<ProductionCountriesItem>, //String

    @field:SerializedName("revenue")
    var revenue: Int,

    @field:SerializedName("production_companies")
    var productionCompanies: List<ProductionCompaniesItem>,

    @field:SerializedName("homepage")
    var homepage: String
)
