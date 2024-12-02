package com.example.spendwise.view

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.spendwise.components.CategoryDropdown
import com.example.spendwise.viewmodel.AddTransactionViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface AddTransactionState {
    data object Idle : AddTransactionState
    data object Loading : AddTransactionState
    data class Success(val message: String) : AddTransactionState
    data class Error(val message: String) : AddTransactionState
}

@Composable
fun AddTransactionScreen(modifier: Modifier = Modifier) {
    val viewModel: AddTransactionViewModel = koinViewModel()
    val state = viewModel.addTransactionState.value
    val categories by viewModel.categories

    AddTransactionScreenContent(
        modifier = modifier,
        state = state,
        categories = categories,
        onAddTransaction = { title, amount, category ->
            viewModel.addTransaction(title, amount, category)
        },
        onReset = {
            viewModel.resetState()
        }
    )
}

@Composable
fun AddTransactionScreenContent(
    modifier: Modifier = Modifier,
    state: AddTransactionState,
    categories: List<String>,
    onAddTransaction: (String, Double, String) -> Unit,
    onReset: () -> Unit
) {
    val context = LocalContext.current
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<String?>(null) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Add Transaction",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount ($)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        CategoryDropdown(
            categories = categories,
            selectedCategory = selectedCategory,
            onCategorySelected = { selectedCategory = it },
            label = "Category",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TransactionButtonsRow(
            title = title,
            amount = amount,
            selectedCategory = selectedCategory,
            onAddTransaction = onAddTransaction,
            onReset = {
                title = ""
                amount = ""
                selectedCategory = null
                onReset()
            },
            state = state
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (state is AddTransactionState.Loading) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        TransactionToastEffect(
            state = state,
            context = context,
            onReset = {
                title = ""
                amount = ""
                selectedCategory = null
                onReset()
            }
        )
    }
}


@Composable
private fun TransactionButtonsRow(
    title: String,
    amount: String,
    selectedCategory: String?,
    onAddTransaction: (String, Double, String) -> Unit,
    onReset: () -> Unit,
    state: AddTransactionState
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = {
                val parsedAmount = amount.toDoubleOrNull() ?: -1.0
                onAddTransaction(
                    title.trim(),
                    parsedAmount,
                    selectedCategory ?: ""
                )
            },
            enabled = state !is AddTransactionState.Loading
        ) {
            Text("Add")
        }
        Button(
            onClick = { onReset() },
            enabled = state !is AddTransactionState.Loading
        ) {
            Text("Reset")
        }
    }
}

@Composable
private fun TransactionToastEffect(
    state: AddTransactionState,
    context: android.content.Context,
    onReset: () -> Unit
) {
    LaunchedEffect(state) {
        when (state) {
            is AddTransactionState.Success -> {
                Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show()
                onReset()
            }
            is AddTransactionState.Error -> {
                Toast.makeText(context, "Unsuccessfully: ${state.message}", Toast.LENGTH_SHORT).show()
                onReset()
            }
            else -> { }
        }
    }
}
