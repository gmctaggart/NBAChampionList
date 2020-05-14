package com.gregmctaggart.nbachampslistapp.modules

import com.gregmctaggart.nbachampslistapp.ListAPI
import com.gregmctaggart.nbachampslistapp.NBAChampionRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideNBAChampionRepository(api: ListAPI): NBAChampionRepository {
        return NBAChampionRepository(api)
    }
}