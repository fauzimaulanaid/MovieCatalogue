package com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item

import com.google.gson.annotations.SerializedName

data class GenresItem(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: Int
)
