package com.example.spendwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.spendwise.ui.theme.SpendWiseTheme
import com.example.spendwise.view.HomeScreen
import com.example.spendwise.view.ListScreen
import com.example.spendwise.view.SettingScreen
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendWiseTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = NavigationRoute.HomeScreen) {
                    composable<NavigationRoute.HomeScreen> {
                        val args = it.toRoute<NavigationRoute.HomeScreen>()
                        HomeScreen(currentTabIndex = args.currentTabIndex){
                            navController.navigate(it)
                        }
                    }
                    composable<NavigationRoute.ListScreen> {
                        val args = it.toRoute<NavigationRoute.ListScreen>()
                        ListScreen(currentTabIndex = args.currentTabIndex){
                            navController.navigate(it)
                        }
                    }
                    composable<NavigationRoute.SettingScreen> {
                        val args = it.toRoute<NavigationRoute.SettingScreen>()
                        SettingScreen(currentTabIndex = args.currentTabIndex) {
                            navController.navigate(it)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
sealed class NavigationRoute(
    var currentTabIndex: Int = 0
) {
    @Serializable
    data object HomeScreen : NavigationRoute()
    @Serializable
    data object ListScreen : NavigationRoute()
    @Serializable
    data object SettingScreen : NavigationRoute()
}