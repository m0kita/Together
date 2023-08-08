package ru.mokita.together.ui.course

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mokita.profnotes.databinding.FragmentCourseBinding
import ru.mokita.together.ui.main.adapter.CoursesAdapter
import ru.mokita.together.util.BaseFragment

@AndroidEntryPoint
class CourseFragment: BaseFragment<FragmentCourseBinding>() {
    private val viewModel: CourseViewModel by viewModels()
    override fun getViewBinding(): FragmentCourseBinding = FragmentCourseBinding.inflate(layoutInflater)

    override fun setupUi() {
        setupCourses()
        setupButtons()
    }

    private fun setupCourses() = with(binding) {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                rv.adapter = CoursesAdapter(it.courses)
            }
        }
    }

    private fun setupButtons() = with(binding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}