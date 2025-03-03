package com.example.testappforcourses.domain.usecase

import com.example.testappforcourses.domain.model.User

interface UsersListRepository {

    suspend fun getUsersUseCase(): List<User>
    suspend fun fetchAndSaveUsers()
}