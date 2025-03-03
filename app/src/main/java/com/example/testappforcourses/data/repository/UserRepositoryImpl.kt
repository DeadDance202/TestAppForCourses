package com.example.testappforcourses.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.example.testappforcourses.data.source.ApiService
import com.example.testappforcourses.data.model.toDomain
import com.example.testappforcourses.domain.model.User
import com.example.testappforcourses.domain.usecase.UsersListRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UsersListRepository {

    override suspend fun getUsersUseCase(): List<User> {
        return try {
            apiService.getUsers().map { it.toDomain() } // Преобразуем DTO в доменную модель
        } catch (e: Exception) {
            emptyList() // Если ошибка, возвращаем пустой список
        }
    }
}