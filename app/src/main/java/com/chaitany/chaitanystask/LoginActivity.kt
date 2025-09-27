package com.chaitany.chaitanystask

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaitany.chaitanystask.databinding.ActivityLoginBinding
import com.chaitany.chaitanystask.utils.PreferenceManager

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferenceManager: PreferenceManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        preferenceManager = PreferenceManager(this)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            if (validateInputs()) {
                loginUser()
            }
        }
        
        binding.tvRegisterLink.setOnClickListener {
   startActivity(Intent(this, RegistrationActivity::class.java))
            finish()
        }
    }
    
    private fun validateInputs(): Boolean {
 val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        
  if (email.isEmpty()) {
            binding.etEmail.error = "Email is required"
            return false
        }

        
  if (password.isEmpty()) {
            binding.etPassword.error = "Password is required"
            return false
        }
        
        return true
    }
    
    private fun loginUser() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        
        val savedUser = preferenceManager.getUser()
        
        if (savedUser != null && savedUser.email == email && savedUser.password == password) {
            preferenceManager.setLoggedIn(true)
            
       Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()

            
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
      if (savedUser == null) {
                preferenceManager.setLoggedIn(true)
      Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
     startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
