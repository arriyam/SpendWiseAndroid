package com.example.spendwise.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoute(
    var currentTabIndex: Int = 0
) {
    @Serializable
    data object SummaryScreen : NavigationRoute()
    @Serializable
    data object ListScreen : NavigationRoute()
    @Serializable
    data object AddScreen : NavigationRoute()
}