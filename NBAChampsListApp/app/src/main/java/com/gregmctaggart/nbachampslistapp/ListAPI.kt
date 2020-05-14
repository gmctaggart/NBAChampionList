package com.gregmctaggart.nbachampslistapp

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

interface ListAPI {
    @GET("v2/5eb582ae3100006a00699684")
    suspend fun getChampionList(): NBAChampionListResponse
}

data class NBAChampionListResponse(
    val items: List<NBAChampion>
)

data class NBAChampion(
    @SerializedName("id")
    val id: Int,

    @SerializedName("year")
    val year: Int,

    @SerializedName("team")
    val teamName: String
)