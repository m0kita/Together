package ru.mokita.profnotes.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.mokita.profnotes.R
import ru.mokita.profnotes.databinding.FragmentSplashBinding
import ru.mokita.profnotes.ui.util.BaseFragment


class SplashFragment: BaseFragment<FragmentSplashBinding>() {
    private val navController by lazy { findNavController() }
    override fun getViewBinding(): FragmentSplashBinding = FragmentSplashBinding.inflate(layoutInflater)

    override fun onResume() {
        super.onResume()
        navController.navigate(R.id.action_splashFragment_to_mainFragment)
    }
}