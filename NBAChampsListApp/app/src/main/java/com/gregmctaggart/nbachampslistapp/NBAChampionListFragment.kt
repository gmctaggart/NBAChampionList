package com.gregmctaggart.nbachampslistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NBAChampionListFragment : Fragment() {

    @Inject
    lateinit var repo: NBAChampionRepository

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: NBAChampionListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()
        bind(view)
        observe()
        initializeList()
        loadData()
    }

    private fun inject() {
        val app = requireActivity().application as NBAChampionListApp
        app.appComponent.inject(this)
    }

    private fun bind(view: View) {
        recyclerView = view.findViewById(R.id.nba_champion_list)
    }

    private fun observe() {
        viewModel = ViewModelProvider(this, NBAChampionListViewModel.Factory(repo)).get(
            NBAChampionListViewModel::class.java
        )
        viewModel.champions.observe(requireActivity(), Observer {
            // TODO: implement me!
        })
    }

    private fun initializeList() {
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = NBAChampionListAdapter()
    }

    private fun loadData() {
        viewModel.fetchList()
    }
}
