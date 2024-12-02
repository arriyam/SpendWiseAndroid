package com.example.spendwise.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.viewmodel.CategoryViewModel
import com.example.spendwise.viewmodel.TransactionListViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface TransactionListState{
    data object Loading: TransactionListState
    data class Success(val items: String): TransactionListState
    data class Error(val message: String): TransactionListState
}

@Composable
fun TransactionListScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<TransactionListViewModel>()
    val state = viewModel.transactionListState.value
    TransactionListScreenContent(modifier = modifier, state = state)
}

@Composable
fun TransactionListScreenContent(modifier: Modifier = Modifier, state: TransactionListState) {
    Text(text = "List Screen")
}