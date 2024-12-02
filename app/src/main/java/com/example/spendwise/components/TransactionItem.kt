package com.example.spendwise.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.spendwise.model.Transaction

@Composable
fun TransactionItem(
    transaction: Transaction,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = transaction.title, style = MaterialTheme.typography.bodyLarge)
            Text(
                text = "Amount: $${transaction.amountInDollars()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Category: ${transaction.category}", style = MaterialTheme.typography.bodyMedium)
        }
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete Transaction",
                tint = MaterialTheme.colorScheme.error
            )
        }
    }
}