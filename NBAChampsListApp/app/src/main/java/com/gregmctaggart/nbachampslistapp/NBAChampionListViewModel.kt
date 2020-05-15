package com.gregmctaggart.nbachampslistapp

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NBAChampionListViewModel(
    private val repo: NBAChampionRepository
) : ViewModel() {

    companion object {
        const val LOCAL_ID = -1
    }

    private val _champions = MutableLiveData<List<NBAChampion>>(listOf())
    val champions: LiveData<List<NBAChampion>>
        get() = _champions

    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean>
        get() = _error

    fun fetchList() {
        viewModelScope.launch {
            val result = repo.fetchChampionList()
            if (result.success) {
                _champions.value = result.data
            } else {
                _error.value = true
            }
        }
    }

    fun addItem(teamName: String, year: Int) {
        val updatedList = mutableListOf<NBAChampion>()
        updatedList.addAll(_champions.value!!)
        updatedList.add(NBAChampion(LOCAL_ID, year, teamName))

        _champions.value = updatedList
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: NBAChampionRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NBAChampionListViewModel(repository) as T
        }
    }
}