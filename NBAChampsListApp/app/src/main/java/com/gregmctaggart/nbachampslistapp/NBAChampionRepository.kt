package com.gregmctaggart.nbachampslistapp

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NBAChampionRepository @Inject constructor(
    private val api: ListAPI
) {

    suspend fun fetchChampionList(): NetworkResource<List<NBAChampion>> {
        val result = NetworkResource<List<NBAChampion>>()

        try {
            val response = api.getChampionList()
            result.data = response.items.distinct()
            result.success = true
        } catch (e: Exception) {
            result.success = false
        }

        return result
    }

}