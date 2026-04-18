package com.example.myapplication.di

import com.example.myapplication.main.data.AppDetailsRepositoryImpl
import com.example.myapplication.main.domain.AppDetailsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAppDetailsRepository(
        impl: AppDetailsRepositoryImpl
    ): AppDetailsRepository
}