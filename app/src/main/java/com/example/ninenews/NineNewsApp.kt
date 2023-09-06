package com.example.ninenews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.ninenews.ui.theme.NineNewsTheme

@Composable
fun NineNewspp() {
    NineNewsTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            NineNewsActions(navController)
        }

        NineNewsNavGraph(
            navController = navController,
            navigateToHome = navigationActions.navigateToHome,
            navigateToDetail = navigationActions.navigateToDetail
        )
    }
}