package com.example.myapplication.main.domain

class GetAppsUseCase(
    private val repository: AppsRepository
) {
    suspend operator fun invoke(): List<AppItem> = repository.getApps()
}