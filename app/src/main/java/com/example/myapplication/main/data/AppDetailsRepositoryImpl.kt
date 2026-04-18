package com.example.myapplication.main.data

import AppDetailsMapper
import com.example.myapplication.main.domain.AppDetails
import com.example.myapplication.main.domain.AppDetailsRepository

class AppDetailsRepositoryImpl(
    private val appApi: AppDetailsApi,
    private val mapper: AppDetailsMapper,
) : AppDetailsRepository {

    override suspend fun get(): AppDetails {
        return mapper.toDomain(appApi.get())
    }
}