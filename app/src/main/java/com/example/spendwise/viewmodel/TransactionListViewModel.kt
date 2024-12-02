package com.example.spendwise.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.view.TransactionListState

import kotlinx.coroutines.launch

class TransactionListViewModel: ViewModel() {

    private val _transactionListState = mutableStateOf<TransactionListState>(TransactionListState.Loading)
    val transactionListState: MutableState<TransactionListState> = _transactionListState

    init {
        viewModelScope.launch {
            _transactionListState.value = TransactionListState.Success("India")
        }
    }

}

