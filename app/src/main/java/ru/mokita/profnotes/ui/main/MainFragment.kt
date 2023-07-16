package ru.mokita.profnotes.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import ru.mokita.profnotes.databinding.FragmentMainBinding
import ru.mokita.profnotes.ui.main.adapter.CoursesAdapter
import ru.mokita.profnotes.ui.model.Course
import ru.mokita.profnotes.ui.util.BaseFragment

class MainFragment: BaseFragment<FragmentMainBinding>() {

    private val viewModel: MainViewModel by viewModels()

    override fun getViewBinding(): FragmentMainBinding = FragmentMainBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
    }

    private fun updateUi() {
        lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                binding.rvLastCourses.adapter = CoursesAdapter(listOf(Course(courseName = "Основы Андроида", courseThemes = listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest")), Course(courseName = "Фрагменты Coroutines", courseThemes = listOf("Binding", "Single Activity", "Custom View", "Coroutines"))))
            }
        }
    }
}