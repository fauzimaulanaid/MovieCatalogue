package com.fauzimaulana.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class NowPlayingMovieResponse(
    @field:SerializedName("id")
    var movieId: Int,

    @field:SerializedName("title")
    var title: String,

    @field:SerializedName("release_date")
    var year: String,

    @field:SerializedName("poster_path")
    var imagePath: String,

    @field:SerializedName("genre_ids")
    var genre: List<Int>
)
