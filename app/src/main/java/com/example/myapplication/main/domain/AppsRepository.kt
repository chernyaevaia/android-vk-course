package com.example.myapplication.main.domain

interface AppsRepository {
    suspend fun getApps(): List<AppItem>
}