package com.example.testappforcourses.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testappforcourses.presentation.viewmodel.UserViewModel
import com.example.testappforcourses.databinding.ActivityMainBinding
import com.example.testappforcourses.domain.model.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter(emptyList()) { user ->
            openUserDetails(user)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }

        viewModel.users.observe(this) { users ->
            userAdapter.updateData(users)
        }
        viewModel.fetchUsersFromApi()
    }

    private fun openUserDetails(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java).apply {
            putExtra("user_name", user.name)
            putExtra("user_email", user.email)
            putExtra("user_phone", user.phone)
            putExtra("user_city", user.city)
        }
        startActivity(intent)
    }
}