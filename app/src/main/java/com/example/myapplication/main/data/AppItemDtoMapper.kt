package com.example.myapplication.main.data

import com.example.myapplication.main.domain.AppItem
import javax.inject.Inject

class AppItemDtoMapper @Inject constructor() {
    fun map(dto: AppItemDto): AppItem = AppItem(
        id = dto.id ?: "",
        title = dto.name ?: "No name",
        subtitle = dto.description ?: "",
        category = dto.category ?: "",
        iconUrl = dto.iconUrl ?: ""
    )
}