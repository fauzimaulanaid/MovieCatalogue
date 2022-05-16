package com.fauzimaulana.moviecatalogue.core.data.source.remote.response

import com.fauzimaulana.moviecatalogue.core.data.source.remote.response.item.DatesItem
import com.google.gson.annotations.SerializedName

data class ListNowPlayingMovieResponse(
	@field:SerializedName("dates")
	val dates: DatesItem,

	@field:SerializedName("page")
	val page: Int,

	@field:SerializedName("total_pages")
	val totalPages: Int,

	@field:SerializedName("results")
	val results: List<NowPlayingMovieResponse>,

	@field:SerializedName("total_results")
	val totalResults: Int
)
