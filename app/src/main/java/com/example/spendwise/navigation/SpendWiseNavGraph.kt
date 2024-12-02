package com.example.spendwise.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spendwise.view.CategoryScreen
import com.example.spendwise.view.ListScreen
import com.example.spendwise.view.AddScreen

@Composable
fun SpendWiseNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.SummaryScreen,
        modifier = modifier
    ) {
        composable<NavigationRoute.SummaryScreen> {
            CategoryScreen()
        }
        composable<NavigationRoute.ListScreen> {
            ListScreen()
        }
        composable<NavigationRoute.AddScreen> {
            AddScreen()
        }
    }
}