package com.example.myapplication.main.data

import com.example.myapplication.main.domain.AppItem
import com.example.myapplication.main.domain.AppsRepository
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val api: AppsApi,
    private val mapper: AppItemDtoMapper,
) : AppsRepository {

    override suspend fun getApps(): List<AppItem> {
        val dtoList = api.getApps()

        return dtoList.map { dto ->
            mapper.map(dto)
        }
    }
}