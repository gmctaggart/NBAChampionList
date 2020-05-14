package com.gregmctaggart.nbachampslistapp

import android.app.Application
import com.gregmctaggart.nbachampslistapp.modules.DataSourceModule
import com.gregmctaggart.nbachampslistapp.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DataSourceModule::class])
interface AppComponent {
    fun inject(NBAChampionListFragment: NBAChampionListFragment)
}

class NBAChampionListApp : Application() {

    private lateinit var _appComponent: AppComponent
    val appComponent: AppComponent
        get() = _appComponent

    override fun onCreate() {
        super.onCreate()

        _appComponent = DaggerAppComponent
            .builder()
            .dataSourceModule(DataSourceModule())
            .networkModule(NetworkModule())
            .build()
    }
}
