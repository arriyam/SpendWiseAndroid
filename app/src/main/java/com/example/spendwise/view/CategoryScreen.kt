package com.example.spendwise.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.spendwise.components.AddCategoryDialog
import com.example.spendwise.components.CategoryList
import com.example.spendwise.components.DeleteDialog
import com.example.spendwise.viewmodel.CategoryViewModel
import org.koin.androidx.compose.koinViewModel

sealed interface CategoryState{
    data object Loading: CategoryState
    data class Success(val categories: List<String>): CategoryState
    data class Error(val message: String): CategoryState
}

@Composable
fun CategoryScreen(modifier: Modifier = Modifier) {
    val viewModel: CategoryViewModel = koinViewModel()
    val state = viewModel.categoryState.value

    CategoryScreenContent(
        modifier = modifier,
        state = state,
        onAddCategory = { viewModel.addCategory(it) },
        onRemoveCategory = { viewModel.removeCategory(it) }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryScreenContent(
    modifier: Modifier = Modifier,
    state: CategoryState,
    onAddCategory: (String) -> Unit,
    onRemoveCategory: (String) -> Unit
) {
    var showAddDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var categoryToDelete by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        },
        content = {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Categories",
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                when (state) {
                    is CategoryState.Loading -> {
                        CircularProgressIndicator()
                    }
                    is CategoryState.Success -> {
                        CategoryList(
                            categories = state.categories,
                            onRemoveCategory = { category ->
                                categoryToDelete = category
                                showDeleteDialog = true
                            }
                        )
                    }
                    is CategoryState.Error -> {
                        Text(text = "Error: ${state.message}")
                    }
                }
            }
        }
    )

    if (showAddDialog) {
        AddCategoryDialog(
            onConfirmAdd = { newCategory ->
                onAddCategory(newCategory)
                showAddDialog = false
            },
            onDismiss = { showAddDialog = false }
        )
    }

    if (showDeleteDialog) {
        DeleteDialog(
            title = "Delete Category",
            description = "Are you sure you want to delete the category \"${categoryToDelete}\"?",
            onConfirm = {
                onRemoveCategory(categoryToDelete)
                showDeleteDialog = false
            },
            onDismiss = { showDeleteDialog = false }
        )
    }
}
