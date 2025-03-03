package com.example.testappforcourses.data.repository

import com.example.testappforcourses.data.local.dao.UserDao
import com.example.testappforcourses.data.source.ApiService
import com.example.testappforcourses.data.model.toDomain
import com.example.testappforcourses.data.model.toEntity
import com.example.testappforcourses.domain.model.User
import com.example.testappforcourses.domain.usecase.UsersListRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : UsersListRepository {

    override suspend fun getUsersUseCase(): List<User> {
        return try {
            apiService.getUsers().map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun fetchAndSaveUsers() {
        try {
            val users = apiService.getUsers().map { it.toEntity() }
            userDao.insertUsers(users)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}