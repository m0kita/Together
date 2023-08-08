package ru.mokita.together.ui.splash

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.mokita.profnotes.R
import ru.mokita.profnotes.databinding.FragmentSplashBinding
import ru.mokita.together.util.BaseFragment

@AndroidEntryPoint
class SplashFragment: BaseFragment<FragmentSplashBinding>() {
    private val navController by lazy { findNavController() }
    override fun getViewBinding(): FragmentSplashBinding = FragmentSplashBinding.inflate(layoutInflater)

    override fun setupUi() {
        navController.navigate(R.id.action_splashFragment_to_loginFragment)
    }
}
