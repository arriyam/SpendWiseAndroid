package com.example.spendwise.model

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.spendwise.navigation.NavigationRoute

data class BottomNavigationItemUiContent(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val navigationRoute: NavigationRoute,
)