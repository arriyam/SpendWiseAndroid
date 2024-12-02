package com.example.spendwise.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.view.SummaryState
import kotlinx.coroutines.launch

class SummaryViewModel: ViewModel() {

    private val _summaryState = mutableStateOf<SummaryState>(SummaryState.Loading)
    val summaryState: MutableState<SummaryState> = _summaryState

    init {
        viewModelScope.launch {
            _summaryState.value = SummaryState.Success("India")
        }
    }
}