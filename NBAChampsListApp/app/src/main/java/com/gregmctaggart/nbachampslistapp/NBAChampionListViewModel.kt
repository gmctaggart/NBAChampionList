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

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: NBAChampionRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NBAChampionListViewModel(repository) as T
        }
    }
}

/*
private val dealRepository: DealRepository
) : ViewModel() {

    private val _deal = MutableLiveData<DealItem?>()
    val deal: LiveData<DealItem?>
        get() = _deal

    fun loadDeal(id: String) {
        viewModelScope.launch {
            _deal.value = dealRepository.loadDeal(id)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: DealRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return DealDetailsFragmentViewModel(repository) as T
        }
    }
}
 */