package ru.mokita.together.ui.registration

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mokita.profnotes.R
import ru.mokita.profnotes.databinding.FragmentRegistrationBinding
import ru.mokita.together.core.textWatcher
import ru.mokita.together.util.BaseFragment

@AndroidEntryPoint
class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {
    private val viewModel: RegistrationViewModel by viewModels()

    override fun getViewBinding(): FragmentRegistrationBinding =
        FragmentRegistrationBinding.inflate(layoutInflater)

    override fun setupUi() {
        setupFields()
        setupButtons()
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                if (it.isTokenValid) {
                    findNavController().navigate(R.id.action_registrationFragment_to_mainFragment)
                }
                binding.btnRegistration.isEnabled =
                    it.name.isNotEmpty() && it.surname.isNotEmpty() && it.phone.isNotEmpty() && it.password.isNotEmpty()
            }
        }
    }

    private fun setupFields() {
        binding.etName.addTextChangedListener(textWatcher { viewModel.updateName(it) })
        binding.etSurname.addTextChangedListener(textWatcher { viewModel.updateSurname(it) })
        binding.etNumber.addTextChangedListener(textWatcher { viewModel.updatePhone(it) })
        binding.etPassword.addTextChangedListener(textWatcher { viewModel.updatePassword(it) })
    }

    private fun setupButtons() {
        binding.btnRegistration.setOnClickListener {
            binding.progress.isVisible = true
            viewModel.registration()
        }
        binding.btnLogin.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}