package com.example.myapplication.main.presentation.appdetails

import androidx.annotation.StringRes

sealed interface AppDetailsEvent {
    data class ShowSnackbar(@StringRes val messageResId: Int) : AppDetailsEvent
}