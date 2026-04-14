package com.example.myapplication.main.data

import com.example.myapplication.main.data.applist.dto.AppItemDto

class AppsListApi {
    fun getApps(): List<AppItemDto> =
        FakeAppData.apps.map {
            AppItemDto(
                id = it.id,
                title = it.title,
                subtitle = it.subtitle,
                category = it.category,
                iconRes = it.iconRes,
            )
        }
}