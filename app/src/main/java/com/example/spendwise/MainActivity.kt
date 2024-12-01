package com.example.spendwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.ui.theme.SpendWiseTheme
import com.example.spendwise.view.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendWiseTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = NavigationRoute.HomeScreen) {
                    composable<NavigationRoute.HomeScreen> {
                        HomeScreen()
                    }
                }
            }
        }
    }
}

sealed interface NavigationRoute {
    data object HomeScreen : NavigationRoute
    data object ListScreen : NavigationRoute
    data object SettingScreen : NavigationRoute
}