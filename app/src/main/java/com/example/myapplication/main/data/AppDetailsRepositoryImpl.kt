package com.example.myapplication.main.data

import com.example.myapplication.main.domain.AppDetails
import com.example.myapplication.main.domain.AppDetailsRepository
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppDetailsApi,
    private val mapper: AppDetailsMapper,
) : AppDetailsRepository {

    override suspend fun get(): AppDetails {
        return mapper.toDomain(appApi.get())
    }
}