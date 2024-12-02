package com.example.spendwise.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.spendwise.viewmodel.CategoryViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface CategoryState{
    data object Loading: CategoryState
    data class Success(val summary: String): CategoryState
    data class Error(val message: String): CategoryState
}

@Composable
fun CategoryScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<CategoryViewModel>()
    val state = viewModel.categoryState.value
    CategoryScreenContent(modifier = modifier, state = state)
}


@Composable
fun CategoryScreenContent(modifier: Modifier = Modifier, state: CategoryState) {
    Text(text = "Home Screen", modifier = modifier)
}