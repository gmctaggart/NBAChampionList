package com.gregmctaggart.nbachampslistapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText

class CreateChampionTeamBottomSheetFragment(private val callback: CreateItemCallback) :
    BottomSheetDialogFragment() {

    interface CreateItemCallback {
        fun onCreateChampion(teamName: String, year: String)
    }

    private lateinit var teamNameTv: TextInputEditText
    private lateinit var yearTv: TextInputEditText
    private lateinit var createButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.create_item_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bind(view)
        initialize()
    }

    private fun bind(view: View) {
        teamNameTv = view.findViewById(R.id.create_item_bottom_sheet_team_name_text)
        yearTv = view.findViewById(R.id.create_item_bottom_sheet_year_text)
        createButton = view.findViewById(R.id.create_item_bottom_sheet_button)
    }

    private fun initialize() {
        createButton.setOnClickListener {
            callback.onCreateChampion(teamNameTv.text.toString(), yearTv.text.toString())
            dismiss()
        }
    }
}
