package com.example.testappforcourses.data.source

import androidx.lifecycle.LiveData
import com.example.testappforcourses.data.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}