package model

import com.google.gson.annotations.SerializedName

data class TVShowListPage(
    val page: Int,
    val results: List<TVShow>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)