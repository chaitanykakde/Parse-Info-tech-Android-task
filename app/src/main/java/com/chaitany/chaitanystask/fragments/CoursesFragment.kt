package com.chaitany.chaitanystask.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaitany.chaitanystask.R
import com.chaitany.chaitanystask.adapters.CourseAdapter
import com.chaitany.chaitanystask.databinding.FragmentCoursesBinding
import com.chaitany.chaitanystask.viewmodel.CoursesViewModel

class CoursesFragment : Fragment() {
    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var viewModel: CoursesViewModel
    private var allCourses: List<com.chaitany.chaitanystask.data.Course> = emptyList()
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupViewModel()
        observeData()
        setupSearch()
    }
    
    private fun setupRecyclerView() {
        courseAdapter = CourseAdapter { course ->
            navigateToCourseDetail(course)
        }
        
        binding.recyclerViewCourses.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = courseAdapter
        }
    }
    
    private fun setupViewModel() {
        viewModel = ViewModelProvider(this)[CoursesViewModel::class.java]
    }
    
    private fun observeData() {
        viewModel.courses.observe(viewLifecycleOwner) { courses ->
            allCourses = courses
            courseAdapter.submitList(courses)
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        viewModel.error.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                // Show error message
                binding.textViewError.text = error
                binding.textViewError.visibility = View.VISIBLE
            } else {
                binding.textViewError.visibility = View.GONE
            }
        }
    }
    
    private fun setupSearch() {
        binding.etSearch.addTextChangedListener(object : android.text.TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: android.text.Editable?) {
                filterCourses(s.toString())
            }
        })
    }
    
    private fun filterCourses(query: String) {
        val filteredCourses = if (query.isEmpty()) {
            allCourses
        } else {
            allCourses.filter { course ->
                course.title.contains(query, ignoreCase = true) ||
                course.description.contains(query, ignoreCase = true) ||
                course.instructor.contains(query, ignoreCase = true) ||
                course.category.contains(query, ignoreCase = true)
            }
        }
        courseAdapter.submitList(filteredCourses)
    }
    
    private fun navigateToCourseDetail(course: com.chaitany.chaitanystask.data.Course) {
        val intent = android.content.Intent(requireContext(), com.chaitany.chaitanystask.CourseDetailActivity::class.java)
        intent.putExtra("course", course)
        startActivity(intent)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
