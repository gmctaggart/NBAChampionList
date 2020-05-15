package com.gregmctaggart.nbachampslistapp

import org.junit.Test

import org.junit.Assert.*

class NBAChampionListAdapterTest {

    companion object {
        const val ID_1 = 1
        const val ID_2 = 2
        const val ID_3 = 3
    }

    @Test
    fun setItems() {
        val adapter = NBAChampionListAdapter()

        var listUpdated = false
        val items = listOf(
            NBAChampion(ID_1, 1992, "Team A"),
            NBAChampion(ID_2, 1993, "Team B"),
            NBAChampion(ID_3, 1994, "Team C")
        )

        adapter.dataChangedCallback = object : NBAChampionListAdapter.DataChangedCallback {
            override fun onDataChanged() {
                listUpdated = true
            }
        }

        adapter.setItems(items)
        assertTrue(listUpdated)
        assertEquals(3, adapter.itemCount)
        for (i in items.indices) {
            val adapterItem = adapter.getItemData()[i]
            assertEquals(items[i].id, adapterItem.id)
        }
    }
}