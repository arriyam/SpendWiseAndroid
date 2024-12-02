package com.example.spendwise.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.view.CategoryState
import kotlinx.coroutines.launch

class CategoryViewModel: ViewModel() {

    private val _categoryState = mutableStateOf<CategoryState>(CategoryState.Loading)
    val categoryState: MutableState<CategoryState> = _categoryState

    init {
        viewModelScope.launch {
            _categoryState.value = CategoryState.Success("India")
        }
    }
}