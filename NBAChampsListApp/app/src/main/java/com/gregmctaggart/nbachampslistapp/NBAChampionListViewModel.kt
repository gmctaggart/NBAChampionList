package com.gregmctaggart.nbachampslistapp

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NBAChampionListViewModel(
    private val repo: NBAChampionRepository
)  : ViewModel() {

    private val _champions = MutableLiveData<List<NBAChampion>>()
    val champions: LiveData<List<NBAChampion>>
        get() = _champions

    fun fetchList() {
        viewModelScope.launch {
            _champions.value = repo.fetchChampionList()
        }
    }

    fun addItem(teamName: String, year:Int) {
        val updatedList = mutableListOf<NBAChampion>()
        updatedList.addAll(_champions.value!!)
        updatedList.add(NBAChampion(0, year, teamName))

        _champions.value = updatedList
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: NBAChampionRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NBAChampionListViewModel(repository) as T
        }
    }
}