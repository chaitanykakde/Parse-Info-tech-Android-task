package com.chaitany.chaitanystask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chaitany.chaitanystask.data.Course
import com.chaitany.chaitanystask.network.RetrofitClient
import kotlinx.coroutines.launch

class CoursesViewModel : ViewModel() {
    
    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> = _courses
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    init {
        loadCourses()
    }
    
    private fun loadCourses() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            try {
 val response = RetrofitClient.apiService.getCourses()
                if (response.isSuccessful) {
                    val courses = response.body()?.record ?: emptyList()
                    _courses.value = courses
                } else {
                    _error.value = "Failed to load courses: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Failed to load courses: ${e.message}"
                val sampleCourses = createSampleCourses()
                      _courses.value = sampleCourses
            } finally {
                _isLoading.value = false
            }
        }
    }

    
    private fun createSampleCourses(): List<Course> {
        return listOf(
            Course(
                id = "1",
                title = "Complete Android Development",
                description = "Learn Android development from scratch with Kotlin and modern tools",
                price = 99.99,
                rating = 4.8f,
                imageUrl = "https://via.placeholder.com/300x200",
                instructor = "John Smith",
                duration = "12 weeks",
                category = "Mobile"
            ),
            Course(
                id = "2",
                title = "Web Development Bootcamp",
                description = "Master HTML, CSS, JavaScript, and React for modern web development",
                price = 149.99,
                rating = 4.9f,
                imageUrl = "https://via.placeholder.com/300x200",
                instructor = "Sarah Johnson",
                duration = "16 weeks",
                category = "Web"
            ),
            Course(
                id = "3",
                title = "Data Science Fundamentals",
                description = "Introduction to data science with Python, statistics, and machine learning",
                price = 199.99,
                rating = 4.7f,
                imageUrl = "https://via.placeholder.com/300x200",
                instructor = "Dr. Michael Chen",
                duration = "20 weeks",
                category = "Data Science"
            ),
            Course(
                id = "4",
                title = "UI/UX Design Masterclass",
                description = "Learn design principles, tools, and create stunning user interfaces",
                price = 129.99,
                rating = 4.6f,
                imageUrl = "https://via.placeholder.com/300x200",
                instructor = "Emma Wilson",
                duration = "10 weeks",
                category = "Design"
            ),
            Course(
                id = "5",
                title = "Cloud Computing with AWS",
                description = "Master Amazon Web Services and cloud architecture",
                price = 179.99,
                rating = 4.8f,
                imageUrl = "https://via.placeholder.com/300x200",
                instructor = "Alex Rodriguez",
                duration = "14 weeks",
                category = "Cloud"
            ),
            Course(
                id = "6",
                title = "Cybersecurity Essentials",
                description = "Learn to protect systems and networks from cyber threats",
                price = 159.99,
                rating = 4.5f,
                imageUrl = "https://via.placeholder.com/300x200",
                instructor = "Lisa Thompson",
                duration = "18 weeks",
                category = "Security"
            )
        )
    }
    
    fun refreshCourses() {
        loadCourses()
    }
}
