package com.example.testappforcourses.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.testappforcourses.domain.model.User
import com.example.testappforcourses.domain.usecase.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    val users: LiveData<List<User>> = getUsersUseCase.execute()
        .asLiveData()

    fun fetchUsersFromApi() {
        viewModelScope.launch(Dispatchers.IO) {
            getUsersUseCase.fetchAndSaveUsers()
        }
    }
}