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

sealed class ListState(
    var currentTabIndex: Int = 0,
) {
    data object Loading: ListState()
    data class Success(val items: List<String>): ListState()
    data class Error(val message: String): ListState()
}

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    currentTabIndex: Int,
    navigateToScreen: (NavigationRoute) -> Unit,
) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state = viewModel.homeState.value
    state.currentTabIndex = currentTabIndex
    ListScreenContent(modifier = modifier, state = state, navigateToScreen = navigateToScreen)
}


@Composable
fun ListScreenContent(
    modifier: Modifier = Modifier,
    state: HomeState,
    navigateToScreen: (NavigationRoute) -> Unit) {
    Scaffold(
        modifier = modifier,
        topBar = { SpendWiseTopAppBar() },
        content = { contentPadding ->
            Text(text = "List Screen", modifier = Modifier.padding(contentPadding))
//            when(state){
//                is ListState.Loading -> Text(text = "Loading", modifier = Modifier.padding(contentPadding))
//                is ListState.Error -> Text(text = state.message, modifier = Modifier.padding(contentPadding))
//                is ListState.Success -> Text(text = state.items.joinToString(), modifier = Modifier.padding(contentPadding))
//            }
        },
        bottomBar = { SpendWiseBottomBar(navigateToScreen = navigateToScreen, currentItemIndex = state.currentTabIndex ) }
    )
}