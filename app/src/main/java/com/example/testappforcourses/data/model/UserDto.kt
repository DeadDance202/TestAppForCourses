package com.example.testappforcourses.data.model

import com.example.testappforcourses.domain.model.User

data class UserDto(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: AddressDto
)

data class AddressDto(val city: String)

fun UserDto.toDomain(): User {
    return User(id, name, email, phone, address.city)
}