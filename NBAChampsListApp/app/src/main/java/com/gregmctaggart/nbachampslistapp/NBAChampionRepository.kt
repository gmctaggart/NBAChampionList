package com.gregmctaggart.nbachampslistapp

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NBAChampionRepository @Inject constructor(
    private val api: ListAPI) {

    suspend fun fetchChampionList(): List<NBAChampion> {
        val champions = mutableListOf<NBAChampion>()

        try {
            val response = api.getChampionList()
            champions.addAll(response.items.distinct())
        } catch (e: Exception) {
            // TODO: Handle failures.
        }

        return champions
    }

}