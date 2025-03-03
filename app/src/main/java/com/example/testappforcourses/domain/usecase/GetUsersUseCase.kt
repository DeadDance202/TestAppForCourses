package com.example.testappforcourses.domain.usecase

import com.example.testappforcourses.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetUsersUseCase @Inject constructor(
    private val repository: UsersListRepository
) {
    fun execute(): Flow<List<User>> = flow {
        emit(repository.getUsersUseCase())
    }.catch { emit(emptyList()) }
}