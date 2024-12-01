package com.example.spendwise.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.components.SpendWiseBottomBar
import com.example.spendwise.components.SpendWiseTopAppBar
import com.example.spendwise.view.HomeScreen
import com.example.spendwise.view.ListScreen
import com.example.spendwise.view.SettingScreen

@Composable
fun SpendWiseNavGraph(showTopBar: Boolean = true, showBottomBar: Boolean = true) {
    val navController = rememberNavController()
    var currentTabIndex by remember { mutableIntStateOf(0) }
    var showTopBar by remember { mutableStateOf(showTopBar) }
    var showBottomBar by remember { mutableStateOf(showBottomBar) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { if (showTopBar) SpendWiseTopAppBar() },
        bottomBar = {
            if (showBottomBar) {
                SpendWiseBottomBar(currentItemIndex = currentTabIndex) { navigationRoute ->
                    currentTabIndex = navigationRoute.currentTabIndex
                    navController.navigate(navigationRoute)
                }
            }
        },
        content = { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = NavigationRoute.HomeScreen,
                modifier = Modifier.padding(innerPadding)
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
    )
}