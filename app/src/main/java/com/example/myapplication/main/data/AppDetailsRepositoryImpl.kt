package com.example.myapplication.main.data

import com.example.myapplication.main.domain.AppDetails
import com.example.myapplication.main.domain.AppDetailsRepository
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val api: AppsApi,
    private val mapper: AppDetailsMapper,
) : AppDetailsRepository {

    override suspend fun get(id: String): AppDetails {
        val dto = api.getAppDetails(id)
        return mapper.toDomain(dto)
    }
}