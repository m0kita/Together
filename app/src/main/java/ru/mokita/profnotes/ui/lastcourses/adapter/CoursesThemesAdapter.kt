package ru.mokita.profnotes.ui.lastcourses.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mokita.profnotes.databinding.ItemCourseThemeBinding

class CoursesThemesAdapter(private val themes: List<String>): RecyclerView.Adapter<CoursesThemesAdapter.CourseThemeViewHolder>() {

    inner class CourseThemeViewHolder(private val binding: ItemCourseThemeBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(theme: String) {
            binding.tvThemeName.text = theme
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseThemeViewHolder {
        val binding = ItemCourseThemeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseThemeViewHolder(binding)
    }

    override fun getItemCount(): Int = themes.size

    override fun onBindViewHolder(holder: CourseThemeViewHolder, position: Int) {
        holder.onBind(themes[position])
    }
}