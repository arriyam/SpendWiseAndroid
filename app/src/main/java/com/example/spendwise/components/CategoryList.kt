package com.example.spendwise.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CategoryList(
    categories: List<String>,
    onRemoveCategory: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(categories) { category ->
            CategoryItem(
                categoryName = category,
                onRemoveCategory = { onRemoveCategory(category) }
            )
        }
    }
}
