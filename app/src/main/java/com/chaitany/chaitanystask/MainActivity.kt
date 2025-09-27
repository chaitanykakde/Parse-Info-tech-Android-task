package com.chaitany.chaitanystask

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chaitany.chaitanystask.utils.PreferenceManager

class MainActivity : AppCompatActivity() {
    private lateinit var preferenceManager: PreferenceManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        preferenceManager = PreferenceManager(this)
        if (preferenceManager.isLoggedIn()) {
 startActivity(Intent(this, HomeActivity::class.java))
        } else {
    startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}