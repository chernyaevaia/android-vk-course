package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.main.presentation.applist.AppsListScreen
import com.example.myapplication.main.presentation.appdetails.AppDetailsScreen

object NavRoutes {
    const val LIST = "list"
    const val DETAILS = "details"
}

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.LIST,
        modifier = modifier
    ) {
        composable(route = NavRoutes.LIST) {
            AppsListScreen(
                onItemClick = { _ -> navController.navigate(NavRoutes.DETAILS) }
            )
        }

        composable(NavRoutes.DETAILS) {
            AppDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}