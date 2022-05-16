package com.fauzimaulana.moviecatalogue.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListOnAirTvResponse(
	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("results")
	val results: List<OnAirTvResponse>
)
