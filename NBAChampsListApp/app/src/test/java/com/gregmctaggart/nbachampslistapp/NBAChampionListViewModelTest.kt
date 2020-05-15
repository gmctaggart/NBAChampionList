package com.gregmctaggart.nbachampslistapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gregmctaggart.nbachampslistapp.NBAChampionListViewModel.Companion.LOCAL_ID
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NBAChampionListViewModelTest {

    companion object {
        const val ID_1 = 1
        const val ID_2 = 2
        const val ID_3 = 3
    }

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockApi: ListAPI

    private lateinit var repo: NBAChampionRepository


    @Before
    fun setUp() {
        mockApiResponse()

        repo = NBAChampionRepository(mockApi)
    }

    private fun mockApiResponse() {
        val mockApiItems = listOf(
            NBAChampion(ID_1, 1992, "Team A"),
            NBAChampion(ID_2, 1993, "Team B"),
            NBAChampion(ID_2, 1993, "Team B"),
            NBAChampion(ID_3, 1994, "Team C")
        )
        val mockResponse = NBAChampionListResponse(mockApiItems)
        runBlocking {
            Mockito.`when`(mockApi.getChampionList())
                .thenReturn(mockResponse)
        }
    }

    @Test
    fun addItem() {
        val viewModel =
            NBAChampionListViewModel.Factory(repo).create(NBAChampionListViewModel::class.java)

        viewModel.fetchList()
        var items = viewModel.champions.getOrAwaitValue()
        assertEquals(3, items.size)
        assertEquals(ID_1, items[0].id)
        assertEquals(ID_2, items[1].id)
        assertEquals(ID_3, items[2].id)

        viewModel.addItem("Team A", 1995)

        items = viewModel.champions.getOrAwaitValue()
        assertEquals(4, items.size)
        assertEquals(ID_1, items[0].id)
        assertEquals(ID_2, items[1].id)
        assertEquals(ID_3, items[2].id)
        assertEquals("Team A", items[3].teamName)
        assertEquals(1995, items[3].year)
    }

}