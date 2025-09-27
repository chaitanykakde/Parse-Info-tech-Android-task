package com.chaitany.chaitanystask

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chaitany.chaitanystask.data.User
import com.chaitany.chaitanystask.databinding.ActivityRegistrationBinding
import com.chaitany.chaitanystask.utils.PreferenceManager

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var preferenceManager: PreferenceManager
    
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
 binding = ActivityRegistrationBinding.inflate(layoutInflater)
               setContentView(binding.root)
        preferenceManager = PreferenceManager(this)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            if (validateInputs()) { registerUser()
            }
        }
        
        binding.tvLoginLink.setOnClickListener {


   startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
    
    private fun validateInputs(): Boolean {


        val fullName = binding.etFullName.text.toString().trim()
     val email = binding.etEmail.text.toString().trim()
        val contactNo = binding.etContactNo.text.toString().trim()
 val password = binding.etPassword.text.toString().trim()
val confirmPassword = binding.etConfirmPassword.text.toString().trim()
    val gender = if (binding.rbMale.isChecked) "Male" else "Female"

        
        if (fullName.isEmpty()) {
            binding.etFullName.error = "Full Name required"


            return false

        }
        
        if (email.isEmpty()) {
            binding.etEmail.error = "Email is required"
            return false
        }
        if(

            email.isEmpty()){ binding.etEmail.error="Email is required and also it shoukd have proper eail fomt"
        }

        if (contactNo.isEmpty())
        { binding.etContactNo.error = "Contact number is required"
            return false
        }
        
        if (password.isEmpty()) {
            binding.etPassword.error =
                "Password is required"
            return false
        }

        if (password != confirmPassword) { binding.etConfirmPassword.error = "Passwords do not match"
            return false
        }
        
        return true
    }
    
    private fun registerUser() {
        val user = User(
          fullName = binding.etFullName.text.toString().trim(),
     email = binding.etEmail.text.toString().trim(),
            contactNo = binding.etContactNo.text.toString().trim(),
        gender = if (binding.rbMale.isChecked) "Male" else "Female",
       password = binding.etPassword.text.toString().trim()
        )
        
        preferenceManager.saveUser(user)
        
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show()
        
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
