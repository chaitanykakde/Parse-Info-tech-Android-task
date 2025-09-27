package com.chaitany.chaitanystask

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.chaitany.chaitanystask.adapters.CourseAdapter
import com.chaitany.chaitanystask.databinding.ActivityWatchlistBinding
import com.chaitany.chaitanystask.utils.PreferenceManager

class WatchlistActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWatchlistBinding
    private lateinit var courseAdapter: CourseAdapter
    private lateinit var preferenceManager: PreferenceManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        preferenceManager = PreferenceManager(this)
        
        setupToolbar()
        setupRecyclerView()
        loadWatchlist()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "My Watchlist"
    }
    
    private fun setupRecyclerView() {
        courseAdapter = CourseAdapter { course ->
            val intent = Intent(this, CourseDetailActivity::class.java)
            intent.putExtra("course", course)
            startActivity(intent)
        }
        
        binding.recyclerViewWatchlist.apply {
            layoutManager = LinearLayoutManager(this@WatchlistActivity)
            adapter = courseAdapter
        }
    }
    
    private fun loadWatchlist() {
        val watchlist = preferenceManager.getWatchlist()
        
        if (watchlist.isEmpty()) {
            binding.textViewEmpty.visibility = View.VISIBLE
            binding.recyclerViewWatchlist.visibility = View.GONE
            binding.textViewEmpty.text = "No courses in your watchlist yet.\nAdd courses to your watchlist to see them here!"
        } else {
            binding.textViewEmpty.visibility = View.GONE
            binding.recyclerViewWatchlist.visibility = View.VISIBLE
            courseAdapter.submitList(watchlist)
        }
    }
    
    override fun onResume() {
        super.onResume()
        loadWatchlist()
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
