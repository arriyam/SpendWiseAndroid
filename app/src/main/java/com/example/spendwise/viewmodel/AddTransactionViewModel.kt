package com.example.spendwise.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.view.AddTransactionState
import kotlinx.coroutines.launch

class AddTransactionViewModel: ViewModel() {

    private val _addTransactionState = mutableStateOf<AddTransactionState>(AddTransactionState.Loading)
    val addTransactionState: MutableState<AddTransactionState> = _addTransactionState

    init {
        viewModelScope.launch {
            _addTransactionState.value = AddTransactionState.Success("India")
        }
    }
}