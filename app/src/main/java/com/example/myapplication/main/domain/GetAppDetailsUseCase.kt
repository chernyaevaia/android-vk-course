package com.example.myapplication.main.domain

import javax.inject.Inject

class GetAppDetailsUseCase @Inject constructor(
    private val repository: AppDetailsRepository
) {
    suspend operator fun invoke(id: String): AppDetails {
        return repository.get(id)
    }
}