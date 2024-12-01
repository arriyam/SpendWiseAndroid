package com.example.spendwise.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface ListState{
    data object Loading: ListState
    data class Success(val items: List<String>): ListState
    data class Error(val message: String): ListState
}

@Composable
fun ListScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state = viewModel.homeState.value
    ListScreenContent(modifier = modifier, state = state)
}


@Composable
fun ListScreenContent(modifier: Modifier = Modifier, state: HomeState) {

    Text(text = "List Screen")
}