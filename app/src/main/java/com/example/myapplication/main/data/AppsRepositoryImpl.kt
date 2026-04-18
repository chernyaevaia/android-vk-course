package com.example.myapplication.main.data

import com.example.myapplication.main.domain.AppItem
import com.example.myapplication.main.domain.AppsRepository
import javax.inject.Inject

class AppsRepositoryImpl @Inject constructor(
    private val api: AppsListApi,
    private val mapper: AppItemDtoMapper,
) : AppsRepository {
    override suspend fun getApps(): List<AppItem> {
        return api.getApps().map(mapper::map)
    }
}