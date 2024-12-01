package com.example.spendwise.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface SettingState {
    data object Loading : SettingState
    data class Success(val settings: Map<String, String>) : SettingState
    data class Error(val message: String) : SettingState
}

@Composable
fun SettingScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<HomeViewModel>()
    val state = viewModel.homeState.value
    SettingScreenContent(modifier = modifier, state = state)
}


@Composable
fun SettingScreenContent(modifier: Modifier = Modifier, state: HomeState) {
    Text(text = "Setting Screen", modifier = modifier)
}