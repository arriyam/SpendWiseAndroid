package com.example.spendwise.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spendwise.view.HomeScreen
import com.example.spendwise.view.ListScreen
import com.example.spendwise.view.SettingScreen

@Composable
fun SpendWiseNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.HomeScreen,
        modifier = modifier
    ) {
        composable<NavigationRoute.HomeScreen> {
            HomeScreen()
        }
        composable<NavigationRoute.ListScreen> {
            ListScreen()
        }
        composable<NavigationRoute.SettingScreen> {
            SettingScreen()
        }
    }
}