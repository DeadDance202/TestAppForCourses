package com.example.testappforcourses.di

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
    fun provideUserRepository(apiService: ApiService): UsersListRepository {
        return UserRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideGetUsersUseCase(repository: UsersListRepository): GetUsersUseCase {
        return GetUsersUseCase(repository)
    }
}