package com.example.spendwise.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.view.AddState
import kotlinx.coroutines.launch

class AddViewModel: ViewModel() {

    private val _addState = mutableStateOf<AddState>(AddState.Loading)
    val addState: MutableState<AddState> = _addState

    init {
        viewModelScope.launch {
            _addState.value = AddState.Success("India")
        }
    }
}