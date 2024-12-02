package com.example.spendwise.view

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.spendwise.components.CategoryDropdown
import com.example.spendwise.components.DeleteDialog
import com.example.spendwise.components.TransactionList
import com.example.spendwise.model.Transaction
import com.example.spendwise.viewmodel.TransactionListViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface TransactionListState {
    data object Loading : TransactionListState
    data class Success(val items: List<Transaction>) : TransactionListState
    data class Error(val message: String) : TransactionListState
}

@Composable
fun TransactionListScreen(modifier: Modifier = Modifier) {
    val viewModel: TransactionListViewModel = koinViewModel()
    val state by viewModel.transactionListState
    val categories by viewModel.categories

    TransactionListScreenContent(
        modifier = modifier,
        state = state,
        categories = categories,
        onDeleteTransaction = { transaction ->
            viewModel.deleteTransaction(transaction)
        },
        onCategorySelected = { category ->
            viewModel.fetchTransactions(category)
        }
    )
}

@Composable
fun TransactionListScreenContent(
    modifier: Modifier = Modifier,
    state: TransactionListState,
    categories: List<String>,
    onDeleteTransaction: (Transaction) -> Unit,
    onCategorySelected: (String?) -> Unit
) {
    var selectedCategory by remember { mutableStateOf<String?>(null) }
    var transactionToDelete by remember { mutableStateOf<Transaction?>(null) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Transactions",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        CategoryDropdown(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { category ->
                selectedCategory = category
                onCategorySelected(category)
            },
            label = "Filter by Category",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is TransactionListState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            is TransactionListState.Success -> {
                if (state.items.isEmpty()) {
                    Text("No transactions found", modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    TransactionList(
                        transactions = state.items,
                        onDeleteTransaction = { transaction ->
                            transactionToDelete = transaction
                        }
                    )
                }
            }
            is TransactionListState.Error -> {
                Text(text = "Error: ${state.message}", color = MaterialTheme.colorScheme.error)
            }
        }
    }

    if (transactionToDelete != null) {
        DeleteDialog(
            title = "Delete Transaction",
            description = "Are you sure you want to delete the transaction \"${transactionToDelete!!.title}\"?",
            onConfirm = {
                onDeleteTransaction(transactionToDelete!!)
                transactionToDelete = null
            },
            onDismiss = { transactionToDelete = null }
        )
    }
}
