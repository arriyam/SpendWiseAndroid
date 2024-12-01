package com.example.spendwise.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.spendwise.model.BottomNavigationItemUiContent
import com.example.spendwise.navigation.SpendWiseNavGraph

@Composable
fun SpendWiseScaffold(
    bottomBarItems: List<BottomNavigationItemUiContent>,
    showTopBar: Boolean = true,
    showBottomBar: Boolean = true,
) {
    val navController = rememberNavController()
    var currentTabIndex by remember { mutableIntStateOf(0) }
    var showTopBar by remember { mutableStateOf(showTopBar) }
    var showBottomBar by remember { mutableStateOf(showBottomBar) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { if (showTopBar) SpendWiseTopAppBar() },
        bottomBar = {
            if (showBottomBar) {
                SpendWiseBottomBar(currentItemIndex = currentTabIndex, items = bottomBarItems) { navigationRoute ->
                    currentTabIndex = navigationRoute.currentTabIndex
                    navController.navigate(navigationRoute)
                }
            }
        },
        content = { innerPadding ->
            SpendWiseNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    )
}