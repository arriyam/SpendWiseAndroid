package com.example.spendwise.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.model.Transaction

@Composable
fun TransactionList(
    transactions: List<Transaction>,
    onDeleteTransaction: (Transaction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(transactions) { transaction ->
            TransactionItem(
                transaction = transaction,
                onDelete = { onDeleteTransaction(transaction) }
            )
            HorizontalDivider()
        }
    }
}