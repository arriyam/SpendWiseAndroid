package com.example.spendwise.provider

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Person
import com.example.spendwise.model.BottomNavigationItemUiContent
import com.example.spendwise.navigation.NavigationRoute

object BottomNavigationItemProvider {
    fun getItems(): List<BottomNavigationItemUiContent> = listOf(
        BottomNavigationItemUiContent(
            title = "Summary",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            navigationRoute = NavigationRoute.HomeScreen,
        ),
        BottomNavigationItemUiContent(
            title = "Transactions",
            selectedIcon = Icons.AutoMirrored.Filled.List,
            unselectedIcon = Icons.AutoMirrored.Outlined.List,
            navigationRoute = NavigationRoute.ListScreen,
        ),
        BottomNavigationItemUiContent(
            title = "Add",
            selectedIcon = Icons.Filled.Add,
            unselectedIcon = Icons.Outlined.Add,
            navigationRoute = NavigationRoute.SettingScreen,
        )
    )
}