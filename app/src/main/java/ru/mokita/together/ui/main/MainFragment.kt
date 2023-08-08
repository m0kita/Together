package ru.mokita.together.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import ru.mokita.profnotes.R
import ru.mokita.profnotes.databinding.FragmentMainBinding
import ru.mokita.together.ui.main.adapter.CoursesAdapter
import ru.mokita.together.domain.model.Course
import ru.mokita.together.util.BaseFragment
import ru.mokita.together.util.ZoomOutPageTransformer

class MainFragment: BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(): FragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
    }

    override fun setupUi() {
        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationBar.setupWithNavController(navController)
    }

}