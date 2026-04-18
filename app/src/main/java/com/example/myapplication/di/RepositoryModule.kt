package com.example.myapplication.di

import com.example.myapplication.main.data.AppDetailsRepositoryImpl
import com.example.myapplication.main.data.AppsRepositoryImpl // Добавили импорт
import com.example.myapplication.main.domain.AppDetailsRepository
import com.example.myapplication.main.domain.AppsRepository // Добавили импорт
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

    @Binds
    @Singleton
    abstract fun bindAppsRepository(
        impl: AppsRepositoryImpl
    ): AppsRepository
}