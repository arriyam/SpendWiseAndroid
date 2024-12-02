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
import com.example.spendwise.view.AddTransactionState
import kotlinx.coroutines.launch

class AddTransactionViewModel(
    private val transactionRepository: TransactionRepository,
    private val categoryManager: CategoryManager
) : ViewModel() {
    private val _addTransactionState = mutableStateOf<AddTransactionState>(AddTransactionState.Idle)
    private val _categories = mutableStateOf<List<String>>(emptyList())
    val addTransactionState: State<AddTransactionState> = _addTransactionState
    val categories: State<List<String>> = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            _addTransactionState.value = AddTransactionState.Loading
            try {
                val categoriesList = categoryManager.getCategories()
                Log.d("AddTransactionViewModel", "Categories: $categoriesList")
                _categories.value = categoriesList
                _addTransactionState.value = AddTransactionState.Idle
            } catch (e: Exception) {
                Log.e("AddTransactionViewModel", "Error loading categories", e)
                _addTransactionState.value = AddTransactionState.Error("Error loading categories")
            }
        }
    }

    fun addTransaction(title: String, amount: Double, category: String) {
        viewModelScope.launch {
            if (title.isBlank() || category.isBlank() || amount <= 0.0) {
                _addTransactionState.value = AddTransactionState.Error("Missing/Invalid values")
                return@launch
            }

            _addTransactionState.value = AddTransactionState.Loading
            try {
                val transaction = Transaction(
                    id = 0,
                    title = title,
                    amountCents = (amount * 100).toLong(),
                    date = System.currentTimeMillis(),
                    category = category,
                    notes = null
                )
                val transactionEntity = TransactionEntity.fromTransaction(transaction)
                transactionRepository.insertTransaction(transactionEntity)
                _addTransactionState.value = AddTransactionState.Success("Successfully added transaction")
            } catch (e: Exception) {
                Log.e("AddTransactionViewModel", "Error adding transaction", e)
                _addTransactionState.value = AddTransactionState.Error("Error adding transaction")
            }
        }
    }

    fun resetState() {
        _addTransactionState.value = AddTransactionState.Idle
    }
}
