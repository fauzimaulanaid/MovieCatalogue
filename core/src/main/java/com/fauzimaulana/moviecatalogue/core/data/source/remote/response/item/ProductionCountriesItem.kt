package com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item

import com.google.gson.annotations.SerializedName

data class ProductionCountriesItem(
    @field:SerializedName("iso_3166_1")
    val iso31661: String,

    @field:SerializedName("name")
    val name: String
)
