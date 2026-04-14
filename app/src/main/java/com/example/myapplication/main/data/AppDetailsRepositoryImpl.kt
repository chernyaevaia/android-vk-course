package com.example.myapplication.main.data

import AppDetailsMapper
import com.example.myapplication.main.domain.AppDetails
import com.example.myapplication.main.domain.AppDetailsRepository


class AppDetailsRepositorImpl : AppDetailsRepository {
    private val appApi = AppDetailsApi()
    private val mapper = AppDetailsMapper()

    override suspend fun get(): AppDetails {
        val dto = appApi.get()
        val domain = mapper.toDomain(dto)
        return domain
    }
}