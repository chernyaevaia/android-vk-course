package com.example.myapplication.main.data

import com.example.myapplication.main.domain.AppDetails
import javax.inject.Inject

class AppDetailsMapper @Inject constructor() {

    fun toDomain(dto: AppDetailsDto): AppDetails = AppDetails(
        name = dto.name,
        developer = dto.developer,
        category = dto.category,
        ageRating = dto.ageRating,
        size = dto.size.toFloat(),
        iconUrl = dto.icon,
        screenshotUrlList = dto.screenshots,
        description = dto.description,
    )
}