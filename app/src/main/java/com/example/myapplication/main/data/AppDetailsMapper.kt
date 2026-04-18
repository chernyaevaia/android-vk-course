package com.example.myapplication.main.data

import com.example.myapplication.main.domain.AppDetails
import com.example.myapplication.main.domain.Category
import javax.inject.Inject

class AppDetailsMapper @Inject constructor() {

    fun toDomain(dto: AppDetailsDto): AppDetails = AppDetails(
        name = dto.name ?: "",
        developer = "Unknown",
        category = Category.APP,
        ageRating = 0,
        size = 0f,
        iconUrl = dto.iconUrl ?: "",
        screenshotUrlList = emptyList(),
        description = dto.description ?: ""
    )
}