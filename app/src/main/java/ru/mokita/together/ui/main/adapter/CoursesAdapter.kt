package ru.mokita.together.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mokita.profnotes.databinding.ItemLastCourseBinding
import ru.mokita.together.domain.model.Course

class CoursesAdapter(private val courses: List<Course>): RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(private val binding: ItemLastCourseBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(course: Course) {
            binding.tvCourseName.text = course.title
            binding.rvCourseThemes.adapter = CourseThemesAdapter(themes = course.tags)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemLastCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun getItemCount(): Int = courses.size

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.onBind(courses[position])
    }
}