package com.example.myapplication.main.data

import com.example.myapplication.main.data.applist.dto.AppItemDto
import javax.inject.Inject

class AppsListApi @Inject constructor() {
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