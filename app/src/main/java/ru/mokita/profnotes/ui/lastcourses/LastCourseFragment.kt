package ru.mokita.profnotes.ui.lastcourses

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import ru.mokita.profnotes.databinding.FragmentLastCourseBinding
import ru.mokita.profnotes.ui.model.Course
import ru.mokita.profnotes.ui.util.BaseFragment

class LastCourseFragment(private val course: Course) : BaseFragment<FragmentLastCourseBinding>() {

    private val viewModel: LastCourseViewModel by viewModels()

    override fun getViewBinding(): FragmentLastCourseBinding =
        FragmentLastCourseBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
    }

    private fun updateUi() {
        viewModel.updateCourse(course = course)
    }
}