package com.example.spendwise.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.local.CategoryManager
import com.example.spendwise.local.TransactionEntity
import com.example.spendwise.model.Transaction
import com.example.spendwise.repository.TransactionRepository
import com.example.spendwise.view.TransactionListState
import kotlinx.coroutines.launch

class TransactionListViewModel(
    private val transactionRepository: TransactionRepository,
    private val categoryManager: CategoryManager
) : ViewModel() {
    private val _transactionListState = mutableStateOf<TransactionListState>(TransactionListState.Loading)
    private val _categories = mutableStateOf<List<String>>(emptyList())
    val transactionListState: State<TransactionListState> = _transactionListState
    val categories: State<List<String>> = _categories

    init {
        fetchCategories()
        fetchTransactions()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val categoriesList = categoryManager.getCategories()
                _categories.value = categoriesList
            } catch (e: Exception) {
                Log.e("TransactionListViewModel", "Error fetching categories", e)
            }
        }
    }

    fun fetchTransactions(category: String? = null) {
        viewModelScope.launch {
            _transactionListState.value = TransactionListState.Loading
            try {
                val transactions = if (category.isNullOrBlank()) {
                    transactionRepository.getAllTransactions().map { it.toTransaction() }
                } else {
                    transactionRepository.getTransactionsBasedOnFilter(category = category).map { it.toTransaction() }
                }
                _transactionListState.value = TransactionListState.Success(transactions)
            } catch (e: Exception) {
                Log.e("TransactionListViewModel", "Error fetching transactions", e)
                _transactionListState.value = TransactionListState.Error(e.message ?: "Error fetching transactions")
            }
        }
    }

    fun deleteTransaction(transaction: Transaction) {
        viewModelScope.launch {
            _transactionListState.value = TransactionListState.Loading
            try {
                transactionRepository.deleteTransaction(TransactionEntity.fromTransaction(transaction))
                fetchTransactions()
            } catch (e: Exception) {
                Log.e("TransactionListViewModel", "Error deleting transaction", e)
                _transactionListState.value = TransactionListState.Error(e.message ?: "Error deleting transaction")
            }
        }
    }
}

