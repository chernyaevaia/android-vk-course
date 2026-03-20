package com.example.myapplication.applist

import com.example.myapplication.AppItem

data class AppsListState(
    val items: List<AppItem> = emptyList(),
    val isLoading: Boolean = true,
)