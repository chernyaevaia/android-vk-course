package com.example.myapplication.main.presentation.appdetails

sealed interface AppDetailsEvent {
    data class ShowSnackbar(val message: String) : AppDetailsEvent
}