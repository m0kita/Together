package ru.mokita.profnotes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import ru.mokita.profnotes.R
import ru.mokita.profnotes.databinding.FragmentMainBinding
import ru.mokita.profnotes.ui.main.adapter.CoursesAdapter
import ru.mokita.profnotes.ui.model.Course
import ru.mokita.profnotes.ui.util.BaseFragment
import ru.mokita.profnotes.ui.util.ZoomOutPageTransformer

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
                binding.vpLastCourses.adapter = CoursesAdapter(listOf(Course(courseName = "Основы Андроида", courseThemes = listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest")),Course(courseName = "Основы Андроида", courseThemes = listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest")),Course(courseName = "Основы Андроида", courseThemes = listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest")),Course(courseName = "Основы Андроида", courseThemes = listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest")),Course(courseName = "Основы Андроида", courseThemes = listOf("View", "Компоненты андроид", "Создание проекта", "Intent", "Manifest")), Course(courseName = "Фрагменты Coroutines", courseThemes = listOf("Binding", "Single Activity", "Custom View", "Coroutines"))))
                binding.vpLastCourses.setPageTransformer(ZoomOutPageTransformer())
            }
        }
        TabLayoutMediator(binding.tbLastCoursesTabs, binding.vpLastCourses) { tab, position ->
        }.attach()
    }
}