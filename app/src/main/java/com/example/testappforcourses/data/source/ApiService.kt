package com.example.testappforcourses.data.source

import com.example.testappforcourses.data.model.UserDto
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}