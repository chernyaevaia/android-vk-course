package com.example.myapplication

import androidx.annotation.DrawableRes

data class AppItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    @DrawableRes val iconRes: Int
)