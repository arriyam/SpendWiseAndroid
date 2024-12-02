package com.example.spendwise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.example.spendwise.components.SpendWiseScaffold
import com.example.spendwise.local.TransactionEntity
import com.example.spendwise.provider.BottomNavigationItemProvider
import com.example.spendwise.provider.RandomTransactionProvider
import com.example.spendwise.repository.TransactionRepository
import com.example.spendwise.ui.theme.SpendWiseTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val transactionRepository: TransactionRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpendWiseTheme {
                SpendWiseScaffold(
                    bottomBarItems = BottomNavigationItemProvider.getItems(),
                    showTopBar = true,
                    showBottomBar = true,
                )
            }
        }
        addTransactions()
    }

    private fun addTransactions() {
        lifecycleScope.launch {
            val transactions = RandomTransactionProvider.getTransactions()
            transactions.forEach { transaction ->
                val transactionEntity = TransactionEntity.fromTransaction(transaction)
                transactionRepository.insertTransaction(transactionEntity)
            }
        }
    }
}