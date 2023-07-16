package ru.mokita.profnotes.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.mokita.profnotes.databinding.FragmentLastCourseBinding
import ru.mokita.profnotes.ui.lastcourses.adapter.CoursesThemesAdapter
import ru.mokita.profnotes.ui.model.Course

class CoursesAdapter(private val courses: List<Course>): RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(private val binding: FragmentLastCourseBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(course: Course) {
            binding.tvCourseName.text = course.courseName
            binding.rvCourseThemes.adapter = CoursesThemesAdapter(themes = course.courseThemes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = FragmentLastCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.onBind(courses[position])
    }
}