package com.example.spendwise.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationRoute(
    var currentTabIndex: Int = 0
) {
    @Serializable
    data object CategoryScreen : NavigationRoute()
    @Serializable
    data object TransactionListScreen : NavigationRoute()
    @Serializable
    data object AddTransactionScreen : NavigationRoute()
}