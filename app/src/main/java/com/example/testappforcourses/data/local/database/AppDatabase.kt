package com.example.testappforcourses.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testappforcourses.data.local.dao.UserDao
import com.example.testappforcourses.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}