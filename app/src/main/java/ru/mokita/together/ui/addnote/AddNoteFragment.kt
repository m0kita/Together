package ru.mokita.together.ui.addnote

import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mokita.profnotes.R
import ru.mokita.profnotes.databinding.FragmentAddNoteBinding
import ru.mokita.together.core.textWatcher
import ru.mokita.together.util.BaseFragment

@AndroidEntryPoint
class AddNoteFragment : BaseFragment<FragmentAddNoteBinding>() {

    private val viewModel: AddNoteViewModel by viewModels()

    override fun getViewBinding(): FragmentAddNoteBinding =
        FragmentAddNoteBinding.inflate(layoutInflater)

    override fun setupUi() {
        setupFields()
        setupButtons()
    }

    private fun setupFields() = with(binding) {
        etName.addTextChangedListener(textWatcher { viewModel.updateTitle(it) })
        etDescription.addTextChangedListener(textWatcher { viewModel.updateDescription(it) })
    }

    private fun setupButtons() = with(binding) {
        btnReady.setOnClickListener {
            viewModel.createNote()
            findNavController().navigate(R.id.action_addNoteFragment_to_homeFragment)
        }
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        btnLocal.setOnClickListener {
            btnLocal.setBackgroundColor(resources.getColor(R.color.yellow))
            btnCommunity.setBackgroundColor(resources.getColor(R.color.light_gray))
            viewModel.updateType("local")
        }
        btnCommunity.setOnClickListener {
            btnCommunity.setBackgroundColor(resources.getColor(R.color.yellow))
            btnLocal.setBackgroundColor(resources.getColor(R.color.light_gray))
            viewModel.updateType("community")
        }
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                btnReady.isEnabled = it.title.isNotEmpty() && it.description.isNotEmpty()
            }
        }
    }

}
