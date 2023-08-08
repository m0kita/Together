package ru.mokita.together.ui.communitynote

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mokita.profnotes.databinding.FragmentCommunityNoteBinding
import ru.mokita.together.ui.communitynote.adapter.CommunityNoteAdapter
import ru.mokita.together.util.BaseFragment

@AndroidEntryPoint
class CommunityNoteFragment: BaseFragment<FragmentCommunityNoteBinding>() {
    private val viewModel: CommunityNoteViewModel by viewModels()
    override fun getViewBinding(): FragmentCommunityNoteBinding = FragmentCommunityNoteBinding.inflate(layoutInflater)

    override fun setupUi() {
        setupNotes()
        setupButtons()
    }

    private fun setupNotes() = with(binding) {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                rv.adapter = CommunityNoteAdapter(it.notes)
            }
        }
    }

    private fun setupButtons() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}