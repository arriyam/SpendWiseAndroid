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


sealed class HomeState(
    var currentTabIndex: Int = 0,
) {
    data object Loading: HomeState()
    data class Success(val country: String): HomeState()
    data class Error(val message: String): HomeState()
}

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    currentTabIndex: Int,
    navigateToScreen: (NavigationRoute) -> Unit,
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state = viewModel.homeState.value
    state.currentTabIndex = currentTabIndex
    HomeScreenContent(modifier = modifier, state = state, navigateToScreen = navigateToScreen)
}


@Composable
fun HomeScreenContent(modifier: Modifier = Modifier, state: HomeState, navigateToScreen: (NavigationRoute) -> Unit) {
    Scaffold(
        modifier = modifier,
        topBar = { SpendWiseTopAppBar() },
        content = { contentPadding ->
            Text(text = "Home Screen", modifier = Modifier.padding(contentPadding))
//            when(state){
//                is HomeState.Loading -> Text(text = "Loading", modifier = Modifier.padding(contentPadding))
//                is HomeState.Error -> Text(text = state.message, modifier = Modifier.padding(contentPadding))
//                is HomeState.Success -> Text(text = state.country, modifier = Modifier.padding(contentPadding))
//            }
        },
        bottomBar = { SpendWiseBottomBar(navigateToScreen = navigateToScreen, currentItemIndex = state.currentTabIndex ) }
    )
}