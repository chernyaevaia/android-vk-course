package com.example.myapplication.main.presentation.applist

sealed interface AppsListEvent {
    data class ShowSnackbar(val message: String) : AppsListEvent
}