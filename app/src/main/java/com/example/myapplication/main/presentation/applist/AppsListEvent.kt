package com.example.myapplication.main.presentation.applist

import androidx.annotation.StringRes

sealed interface AppsListEvent {
    data class ShowSnackbar(@StringRes val messageResId: Int) : AppsListEvent
}