package com.example.ninenews

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ninenews.model.Assets
import com.example.ninenews.ui.screens.DetailsScreen
import com.example.ninenews.ui.screens.NewsList
import com.example.ninenews.ui.screens.NewsViewModel

@Composable
fun NineNewsNavGraph(
    modifier: Modifier = Modifier,
    navigateToHome: () -> Unit,
    navigateToDetail: (Assets) -> Unit,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            NewsList(
                onItemClicked = { navigateToDetail(it) },
                viewModel(factory = NewsViewModel.Factory)
            )
        }

        composable(
            route = Screen.Detail.route
        ) { navBackStackEntry ->

            val newsUrl = navBackStackEntry.arguments?.getString("newsUrl")
            newsUrl?.let {
                DetailsScreen(it)
            }
        }

    }
}