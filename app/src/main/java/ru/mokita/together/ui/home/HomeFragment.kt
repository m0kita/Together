package ru.mokita.together.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mokita.profnotes.R
import ru.mokita.profnotes.databinding.FragmentHomeBinding
import ru.mokita.together.domain.model.Course
import ru.mokita.together.ui.main.MainViewModel
import ru.mokita.together.ui.main.adapter.CoursesAdapter
import ru.mokita.together.util.BaseFragment
import ru.mokita.together.util.ZoomOutPageTransformer
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()

    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)

    override fun setupUi() {
        setupCourses()
        setupLocalNote()
        setupCommunityNote()
        setupButtons()
    }

    private fun setupCourses() = with(binding) {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest { uiState ->
                vpLastCourses.adapter = CoursesAdapter(viewModel.uiState.value.courses)
                vpLastCourses.setPageTransformer(ZoomOutPageTransformer())
            }
        }
        TabLayoutMediator(tbLastCoursesTabs, vpLastCourses) { tab, position ->
        }.attach()
    }

    private fun setupLocalNote() = with(binding) {
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                val note = viewModel.uiState.value.localNote
                if (note.title.isNotBlank()) {
                    tvNoteTitle.text = note.title
                    tvNoteDescription.text = note.description
                    tvNoteDate.text = note.date
                }
            }
        }
    }

    private fun setupCommunityNote() = with(binding){
        lifecycleScope.launch {
            viewModel.uiState.collectLatest {
                if(it.communityNote.date.isNotEmpty()) {
                    val note = viewModel.uiState.value.communityNote
                    val date = Date()
                    date.time = note.date.toLong()
                    val dateFormat = SimpleDateFormat("d MMMM", Locale("ru", "RU"))
                    tvCommunityNoteDate.text = dateFormat.format(date)
                    tvCommunityNoteTitle.text = note.title
                    tvUserName.text = note.author?.name
                }
            }
        }
    }

    private fun setupButtons() = with(binding) {
        btnYourNotesAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
        }
        btnLastCoursesAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_courseFragment)
        }
        btnCommunityNotesAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_communityNoteFragment)
        }
    }
}