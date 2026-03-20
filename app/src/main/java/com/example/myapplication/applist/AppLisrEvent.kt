package com.example.myapplication.applist

sealed interface AppsListEvent {
    data class ShowSnackbar(val message: String) : AppsListEvent
}