package com.example.spendwise.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.viewmodel.AddViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface AddState {
    data object Loading : AddState
    data class Success(val add: String) : AddState
    data class Error(val message: String) : AddState
}

@Composable
fun AddScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<AddViewModel>()
    val state = viewModel.addState.value
    AddScreenContent(modifier = modifier, state = state)
}

@Composable
fun AddScreenContent(modifier: Modifier = Modifier, state: AddState) {
    Text(text = "Setting Screen", modifier = modifier)
}