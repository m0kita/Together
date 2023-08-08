package ru.mokita.together.ui.note

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mokita.profnotes.databinding.FragmentNoteBinding
import ru.mokita.together.ui.main.adapter.CoursesAdapter
import ru.mokita.together.ui.note.adapter.NoteAdapter
import ru.mokita.together.util.BaseFragment

@AndroidEntryPoint
class NoteFragment: BaseFragment<FragmentNoteBinding>() {
    private val viewModel: NoteViewModel by viewModels()
    override fun getViewBinding(): FragmentNoteBinding = FragmentNoteBinding.inflate(layoutInflater)

    override fun setupUi() {
        setupNotes()
        setupButtons()
    }

    private fun setupNotes() = with(binding) {
        lifecycleScope.launch{
            viewModel.uiState.collectLatest {
                rv.adapter = NoteAdapter(it.notes)
            }
        }
    }

    private fun setupButtons() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}