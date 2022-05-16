package com.fauzimaulana.moviecatalogue.core.data.source.remote.response

import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item.*
import com.google.gson.annotations.SerializedName

data class TvResponse(
    @field:SerializedName("id")
    var tvId: Int,

    @field:SerializedName("name")
    var title: String,

    @field:SerializedName("genres")
    var genre: List<GenresItem>, //String

    @field:SerializedName("poster_path")
    var imagePath: String,

    @field:SerializedName("vote_average")
    var rating: Double,

    @field:SerializedName("episode_run_time")
    var runtime: List<Int>, //String

    @field:SerializedName("created_by")
    var creator: List<CreatedByItem>, //String

    @field:SerializedName("overview")
    var synopsys: String,

    @field:SerializedName("number_of_seasons")
    var seasons: Int,

    @field:SerializedName("first_air_date")
    var firstAirDate: String,

    @field:SerializedName("spoken_languages")
    var language: List<SpokenLanguagesItem>, //String

    @field:SerializedName("production_countries")
    var countriesOfOrigin: List<ProductionCountriesItem>, //String

    @field:SerializedName("number_of_episodes")
    var episodes: Int,

    @field:SerializedName("production_companies")
    var productionCompanies: List<ProductionCompaniesItem>, //String

    @field:SerializedName("homepage")
    var homepage: String
)
