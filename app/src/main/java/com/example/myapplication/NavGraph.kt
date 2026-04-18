package com.example.myapplication

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.main.presentation.applist.AppsListScreen
import com.example.myapplication.main.presentation.appdetails.AppDetailsScreen

object NavRoutes {
    const val LIST = "list"
    const val DETAILS_ROUTE = "details/{id}"

    fun createDetailsRoute(id: String): String {
        return "details/$id"
    }
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
                onItemClick = { appItem ->
                    navController.navigate(NavRoutes.createDetailsRoute(appItem.id))
                }
            )
        }

        composable(
            route = NavRoutes.DETAILS_ROUTE,
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) {
            AppDetailsScreen(
                modifier = Modifier.fillMaxSize(),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}