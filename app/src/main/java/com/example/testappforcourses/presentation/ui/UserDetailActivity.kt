package com.example.testappforcourses.presentation.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.testappforcourses.R
import com.example.testappforcourses.databinding.ActivityUserDetailBinding

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvUserName.text = intent.getStringExtra("user_name")
        binding.tvUserEmail.text = intent.getStringExtra("user_email")
        binding.tvUserPhone.text = intent.getStringExtra("user_phone")
        binding.tvUserCity.text = intent.getStringExtra("user_city")
    }
}