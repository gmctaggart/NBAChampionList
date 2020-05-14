package com.gregmctaggart.nbachampslistapp

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class NBAChampionListDeserializer(private val gson: Gson) : JsonDeserializer<NBAChampionListResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): NBAChampionListResponse {
        val payload = json!!.asJsonArray
        val result = mutableListOf<NBAChampion>()

        for (item in payload) {
            val champ = gson.fromJson(item, NBAChampion::class.java)
            result.add(champ)
        }

        return NBAChampionListResponse(result)
    }
}