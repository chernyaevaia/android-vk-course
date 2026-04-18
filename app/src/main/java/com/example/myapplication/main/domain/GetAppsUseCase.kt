package com.example.myapplication.main.domain
import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppsRepository
) {
    suspend operator fun invoke(): List<AppItem> = repository.getApps()
}