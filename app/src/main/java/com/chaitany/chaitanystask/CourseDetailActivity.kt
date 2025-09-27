package com.chaitany.chaitanystask

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.chaitany.chaitanystask.data.Course
import com.chaitany.chaitanystask.databinding.ActivityCourseDetailBinding
import com.chaitany.chaitanystask.utils.PreferenceManager

class CourseDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCourseDetailBinding
    private lateinit var course: Course
    private lateinit var preferenceManager: PreferenceManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        course = intent.getParcelableExtra("course") ?: return
        
        preferenceManager = PreferenceManager(this)
        
        setupToolbar()
        setupCourseDetails()
        setupClickListeners()
        updateWatchlistButton()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Course Details"
    }
    
    private fun setupCourseDetails() {
        binding.apply {
            textViewTitle.text = course.title
            textViewDescription.text = course.description
            textViewInstructor.text = "Instructor: ${course.instructor}"
            textViewDuration.text = "Duration: ${course.duration}"
            textViewCategory.text = course.category
            textViewPrice.text = "$${String.format("%.2f", course.price)}"
            textViewRating.text = "⭐ ${course.rating}"
            
            Glide.with(this@CourseDetailActivity)
                .load(course.imageUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageViewCourse)
        }
    }
    
    private fun setupClickListeners() {
        binding.btnEnroll.setOnClickListener {
            Toast.makeText(this, "Enrollment feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        binding.btnAddToWishlist.setOnClickListener {
            toggleWatchlist()
        }
    }
    
    private fun updateWatchlistButton() {
        if (preferenceManager.isInWatchlist(course.id)) {
            binding.btnAddToWishlist.text = "Remove from Watchlist"
        } else {
            binding.btnAddToWishlist.text = "Add to Watchlist"
        }
    }
    
    private fun toggleWatchlist() {
        if (preferenceManager.isInWatchlist(course.id)) {
            preferenceManager.removeFromWatchlist(course.id)
            binding.btnAddToWishlist.text = "Add to Watchlist"
            Toast.makeText(this, "Removed from watchlist", Toast.LENGTH_SHORT).show()
        } else {
            preferenceManager.addToWatchlist(course)
            binding.btnAddToWishlist.text = "Remove from Watchlist"
            Toast.makeText(this, "Added to watchlist!", Toast.LENGTH_SHORT).show()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
