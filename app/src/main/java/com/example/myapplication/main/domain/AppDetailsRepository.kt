package com.example.myapplication.main.domain

interface AppDetailsRepository {
    suspend fun get(id: String): AppDetails
}