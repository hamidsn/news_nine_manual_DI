package com.example.ninenews

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.ninenews.model.Assets
import com.example.ninenews.util.navigate

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail?id={id}")
}

class NineNewsActions(navController: NavController) {
    val navigateToHome: () -> Unit = {
        navController.navigate(Screen.Home.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val navigateToDetail = { id: Assets ->

        navController.navigate(
            Screen.Detail.route, bundleOf("newsUrl" to id.url)
        )

    }
}