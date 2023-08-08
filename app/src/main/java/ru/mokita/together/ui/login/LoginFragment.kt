package ru.mokita.together.ui.login

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mokita.profnotes.R
import ru.mokita.profnotes.databinding.FragmentLoginBinding
import ru.mokita.together.core.textWatcher
import ru.mokita.together.util.BaseFragment

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()
    override fun getViewBinding(): FragmentLoginBinding =
        FragmentLoginBinding.inflate(layoutInflater)

    override fun setupUi() {
        setupFields()
        setupButtons()
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                if (it.isTokenValid) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
                binding.btnLogin.isEnabled =
                    it.phone.isNotEmpty() && it.password.isNotEmpty()
            }
        }
    }

    private fun setupFields() {
        binding.etNumber.addTextChangedListener(textWatcher { viewModel.updatePhone(it) })
        binding.etPassword.addTextChangedListener(textWatcher { viewModel.updatePassword(it) })
    }

    private fun setupButtons() {
        binding.btnLogin.setOnClickListener {
            binding.progress.isVisible = true
            viewModel.login()
        }
        binding.btnRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }
}