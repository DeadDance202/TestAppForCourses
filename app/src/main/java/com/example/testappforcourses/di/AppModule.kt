package com.example.testappforcourses.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.testappforcourses.data.local.dao.UserDao
import com.example.testappforcourses.data.local.database.AppDatabase
import com.example.testappforcourses.data.source.ApiService
import com.example.testappforcourses.data.repository.UserRepositoryImpl
import com.example.testappforcourses.domain.usecase.GetUsersUseCase
import com.example.testappforcourses.domain.usecase.UsersListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGetUsersUseCase(repository: UsersListRepository): GetUsersUseCase {
        return GetUsersUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, userDao: UserDao): UsersListRepository {
        return UserRepositoryImpl(apiService, userDao)
    }
}