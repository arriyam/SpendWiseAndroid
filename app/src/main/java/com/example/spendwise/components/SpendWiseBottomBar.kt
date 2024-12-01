package com.example.spendwise.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.spendwise.model.BottomNavigationItemUiContent
import com.example.spendwise.navigation.NavigationRoute

@Composable
fun SpendWiseBottomBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationItemUiContent>,
    currentItemIndex: Int = 0,
    navigateToScreen: (NavigationRoute) -> Unit) {

    var selectedItemIndex by remember { mutableIntStateOf(0) }
    selectedItemIndex = currentItemIndex
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    selectedItemIndex = index
                    item.navigationRoute.currentTabIndex = selectedItemIndex
                    navigateToScreen(item.navigationRoute)
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = true,
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon,
                        contentDescription = item.title
                    )
                }
            )
        }
    }
}