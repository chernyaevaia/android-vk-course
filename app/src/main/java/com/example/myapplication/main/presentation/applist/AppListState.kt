package com.example.myapplication.main.presentation.applist

import com.example.myapplication.main.domain.AppItem

data class AppsListState(
    val items: List<AppItem> = emptyList(),
    val isLoading: Boolean = true,
)