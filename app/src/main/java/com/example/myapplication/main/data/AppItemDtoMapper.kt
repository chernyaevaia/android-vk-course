package com.example.myapplication.main.data

import com.example.myapplication.main.data.applist.dto.AppItemDto
import com.example.myapplication.main.domain.AppItem
import javax.inject.Inject

class AppItemDtoMapper @Inject constructor(){
    fun map(dto: AppItemDto): AppItem = AppItem(
        id = dto.id,
        title = dto.title,
        subtitle = dto.subtitle,
        category = dto.category,
        iconRes = dto.iconRes,
    )
}