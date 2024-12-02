package com.example.spendwise.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spendwise.local.CategoryManager
import com.example.spendwise.view.CategoryState
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val categoryManager: CategoryManager
): ViewModel() {
    private val _categoryState = mutableStateOf<CategoryState>(CategoryState.Loading)
    val categoryState: MutableState<CategoryState> = _categoryState

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            val categories = categoryManager.getCategories()
            _categoryState.value = CategoryState.Success(categories)
        }
    }

    fun addCategory(category: String) {
        categoryManager.addCategory(category)
        loadCategories()
    }

    fun removeCategory(category: String) {
        categoryManager.removeCategory(category)
        loadCategories()
    }
}
