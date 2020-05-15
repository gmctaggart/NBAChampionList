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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class NBAChampionListFragment : Fragment() {

    @Inject
    lateinit var repo: NBAChampionRepository

    private lateinit var recyclerView: RecyclerView
    private lateinit var createButton: FloatingActionButton
    private lateinit var viewModel: NBAChampionListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nba_champion_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inject()
        bind(view)
        observe()
        initializeList()
        initializeButton()
        loadData()
    }

    private fun inject() {
        val app = requireActivity().application as NBAChampionListApp
        app.appComponent.inject(this)
    }

    private fun bind(view: View) {
        recyclerView = view.findViewById(R.id.nba_champion_list)
        createButton = requireActivity().findViewById(R.id.fab)
    }

    private fun observe() {
        viewModel = ViewModelProvider(this, NBAChampionListViewModel.Factory(repo)).get(
            NBAChampionListViewModel::class.java
        )

        viewModel.champions.observe(requireActivity(), Observer {
            val adapter = recyclerView.adapter as NBAChampionListAdapter
            adapter.setItems(it)
        })

        viewModel.error.observe(requireActivity(), Observer { hasError ->
            if (hasError) {
                val snackbar = Snackbar.make(
                    requireView(),
                    getString(R.string.network_error_message),
                    Snackbar.LENGTH_SHORT
                )
                snackbar.show()
            }
        })
    }

    private fun initializeList() {
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = NBAChampionListAdapter()
    }

    private fun initializeButton() {
        createButton.setOnClickListener { view ->
            displayCreateBottomSheet()
        }
    }

    private fun loadData() {
        viewModel.fetchList()
    }

    private fun displayCreateBottomSheet() {
        val bottomSheetFragment = CreateChampionTeamBottomSheetFragment(
            object : CreateChampionTeamBottomSheetFragment.CreateItemCallback {
                override fun onCreateChampion(teamName: String, year: String) {
                    viewModel.addItem(teamName, year.toInt())
                }
            })
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)
    }
}
