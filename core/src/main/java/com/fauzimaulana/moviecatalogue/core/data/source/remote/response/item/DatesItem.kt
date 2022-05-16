package com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item

import com.google.gson.annotations.SerializedName

data class DatesItem(
    @field:SerializedName("maximum")
    val maximum: String,

    @field:SerializedName("minimum")
    val minimum: String
)
