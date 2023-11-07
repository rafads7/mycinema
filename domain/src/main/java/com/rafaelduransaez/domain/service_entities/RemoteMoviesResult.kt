package com.rafaelduransaez.domain.service_entities

import com.google.gson.annotations.SerializedName

data class RemoteMoviesResult(
    val page: Int,
    val results: List<RemoteMovie>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)