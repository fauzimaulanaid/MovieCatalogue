package com.fauzimaulana.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class OnAirTvResponse(
    @field:SerializedName("id")
    var tvShowId: Int,

    @field:SerializedName("name")
    var title: String,

    @field:SerializedName("first_air_date")
    var year: String,

    @field:SerializedName("poster_path")
    var imagePath: String,

    @field:SerializedName("genre_ids")
    var genre: List<Int>
)
