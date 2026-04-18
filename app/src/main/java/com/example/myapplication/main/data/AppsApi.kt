package com.example.myapplication.main.data

import retrofit2.http.GET
import retrofit2.http.Path

interface AppsApi {
    @GET("catalog")
    suspend fun getApps(): List<AppItemDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto // Замени AppDetailsDto на свой класс
}