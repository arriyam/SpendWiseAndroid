package com.example.spendwise.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface HomeState{
    data object Loading: HomeState
    data class Success(val country: String): HomeState
    data class Error(val message: String): HomeState
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state = viewModel.homeState.value
    HomeScreenContent(modifier = modifier, state = state)
}


@Composable
fun HomeScreenContent(modifier: Modifier = Modifier, state: HomeState) {
    Text(text = "Home Screen", modifier = modifier)
}