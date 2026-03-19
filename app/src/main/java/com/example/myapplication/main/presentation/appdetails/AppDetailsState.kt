package com.example.myapplication.main.presentation.appdetails

data class AppDetailsState(
    val isLoading: Boolean = true,
    val app: AppDetails? = null,
    val isDescriptionExpanded: Boolean = false,
)