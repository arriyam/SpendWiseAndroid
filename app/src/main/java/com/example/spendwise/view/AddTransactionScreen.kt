package com.example.spendwise.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.viewmodel.AddTransactionViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface AddTransactionState {
    data object Loading : AddTransactionState
    data class Success(val add: String) : AddTransactionState
    data class Error(val message: String) : AddTransactionState
}

@Composable
fun AddTransactionScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<AddTransactionViewModel>()
    val state = viewModel.addTransactionState.value
    AddTransactionScreenContent(modifier = modifier, state = state)
}

@Composable
fun AddTransactionScreenContent(modifier: Modifier = Modifier, state: AddTransactionState) {
    Text(text = "Add Screen", modifier = modifier)
}