package com.example.myapplication.main.presentation.appdetails

import com.example.myapplication.main.domain.AppDetails

data class AppDetailsState(
    val isLoading: Boolean = true,
    val app: AppDetails? = null,
    val isDescriptionExpanded: Boolean = false,
)