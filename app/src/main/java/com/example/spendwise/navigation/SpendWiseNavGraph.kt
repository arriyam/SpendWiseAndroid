package com.example.spendwise.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.spendwise.view.CategoryScreen
import com.example.spendwise.view.TransactionListScreen
import com.example.spendwise.view.AddTransactionScreen

@Composable
fun SpendWiseNavGraph(
    navController: NavHostController,
    startDestination: NavigationRoute,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<NavigationRoute.CategoryScreen> {
            CategoryScreen()
        }
        composable<NavigationRoute.TransactionListScreen> {
            TransactionListScreen()
        }
        composable<NavigationRoute.AddTransactionScreen> {
            AddTransactionScreen()
        }
    }
}