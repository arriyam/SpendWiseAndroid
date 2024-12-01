package com.example.spendwise.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.NavigationRoute
import com.example.spendwise.components.SpendWiseBottomBar
import com.example.spendwise.components.SpendWiseTopAppBar
import com.example.spendwise.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

sealed class SettingState(
    var currentTabIndex: Int = 0,
) {
    data object Loading: SettingState()
    data class Success(val settings: Map<String, String>): SettingState()
    data class Error(val message: String): SettingState()
}

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    currentTabIndex: Int,
    navigateToScreen: (NavigationRoute) -> Unit,
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state = viewModel.homeState.value
    state.currentTabIndex = currentTabIndex
    SettingScreenContent(modifier = modifier, state = state, navigateToScreen = navigateToScreen)
}


@Composable
fun SettingScreenContent(modifier: Modifier = Modifier, state: HomeState, navigateToScreen: (NavigationRoute)-> Unit) {
    Scaffold(
        modifier = modifier,
        topBar = { SpendWiseTopAppBar() },
        content = { contentPadding ->
            Text(text = "Setting Screen", modifier = Modifier.padding(contentPadding))
//            when(state){
//                is SettingState.Loading -> Text(text = "Loading", modifier = Modifier.padding(contentPadding))
//                is SettingState.Error -> Text(text = state.message, modifier = Modifier.padding(contentPadding))
//                is SettingState.Success -> Text(text = state.settings.entries.joinToString { "${it.key}: ${it.value}" }, modifier = Modifier.padding(contentPadding))
//            }
        },
        bottomBar = { SpendWiseBottomBar(navigateToScreen = navigateToScreen, currentItemIndex = state.currentTabIndex ) }
    )
}